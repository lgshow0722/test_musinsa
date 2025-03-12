package com.test.musinsa.service;

import com.test.musinsa.api.response.ExceptionHandlerUtil;
import com.test.musinsa.dto.Question1Dto;
import com.test.musinsa.dto.MerchandiseDto;
import com.test.musinsa.dto.Question2Dto;
import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

import static com.test.musinsa.api.response.ExceptionHandlerUtil.wrapWithExceptionHandling;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final MerchandiseRepository merchandiseRepository;
    private final CategoryRepository categoryRepository;

    // 숫자 포매터 적용
    private final NumberFormat format = NumberFormat.getNumberInstance();

    /**
     * 카테고리별 브랜드 최저가 API
     * @return Question1Dto
     */
    public Question1Dto getCategoryLowestPrice() {
        return wrapWithExceptionHandling(() -> {
            // 각 카테고리별 최저가 상품을 DB에서 가져옴 ( nativeQuery 사용 )
            List<Merchandise> lowestPriceMerchandises = merchandiseRepository.findLowestPricePerCategory();

            // 총액이 추가로 필요하여 stream으로 처리함
            List<MerchandiseDto> merchandiseDtoList = new ArrayList<>();
            long totalPrice = lowestPriceMerchandises.stream()
                    .map(m -> {
                        // Merchandise -> MerchandiseDto로 변환하여 merchandiseDtoList에 추가
                        MerchandiseDto dto = new MerchandiseDto(
                                m.getCategory().getName(),
                                m.getBrand().getName(),
                                format.format(m.getPrice())
                        );
                        merchandiseDtoList.add(dto);
                        return m.getPrice();
                    })
                    .mapToLong(Long::longValue)
                    .sum(); // 총액

            // Question1Dto 저장하여 리턴
            return new Question1Dto(merchandiseDtoList, format.format(totalPrice));
        });
    }

    /**
     * 브랜드별 카테고리 최저가 API
     * @return Question2Dto
     */
    public Question2Dto getBrandLowestPrice() {
        return wrapWithExceptionHandling(() -> {
            // DB에서 카테고리별 브랜드 최저가 데이터를 조회
            List<Object[]> lowestMerchandiseList = merchandiseRepository.findLowestPricePerCategoryByBrand();

            // 전체 카테고리 갯수를 조회
            int totalCategories = categoryRepository.findAll().size();

            // 예외 처리 : 카테고리가 존재하지 않는 경우
            if (totalCategories == 0) {
                throw new IllegalArgumentException("카테고리가 존재하지 않습니다.");
            }

            // 두가지의 해시맵을 활용하여 데이터를 비교,분석한다.
            Map<String, Long> brandPriceMap = new HashMap<>();
            Map<String, List<MerchandiseDto>> merchandiseMap = new HashMap<>();

            for (Object[] row : lowestMerchandiseList) {
                String categoryName = (String) row[0];
                String brandName = (String) row[1];
                Long price = (Long) row[2];

                // 브랜드 총합 계산
                brandPriceMap.put(brandName, brandPriceMap.getOrDefault(brandName, 0L) + price);

                // 브랜드별 MerchandiseDto 생성 및 저장
                if (!merchandiseMap.containsKey(brandName)) {
                    merchandiseMap.put(brandName, new ArrayList<>());
                }

                // 브랜드명은 출력하지 않기 때문에 null로 처리함
                merchandiseMap.get(brandName).add(new MerchandiseDto(categoryName, null, format.format(price)));
            }

            // 최저가 브랜드와 해당 브랜드의 가격을 뽑아낸다.
            String lowestBrand = null;
            long lowestPrice = Long.MAX_VALUE;

            for (Map.Entry<String, Long> entry : brandPriceMap.entrySet()) {
                String brandName = entry.getKey();
                long totalPrice = entry.getValue();

                // 카테고리의 갯수가 동일하고, 직전 최저가 보다 작은 경우 저장
                if (merchandiseMap.getOrDefault(brandName, new ArrayList<>()).size() == totalCategories && totalPrice < lowestPrice) {
                    lowestBrand = brandName;
                    lowestPrice = totalPrice;
                }
            }

            // 예외 처리 : 최저가 브랜드가 없는 경우
            if (lowestBrand == null) {
                throw new IllegalArgumentException("모든 카테고리를 커버하는 브랜드가 없습니다.");
            }

            // Question2Dto 저장하여 리턴
            Question2Dto.Question2Inner inner = new Question2Dto.Question2Inner(lowestBrand, merchandiseMap.get(lowestBrand), format.format(lowestPrice));
            return new Question2Dto(inner);
        });
    }
}
