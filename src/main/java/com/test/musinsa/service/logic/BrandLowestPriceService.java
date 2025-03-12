package com.test.musinsa.service.logic;

import com.test.musinsa.dto.MerchandiseDto;
import com.test.musinsa.dto.Question2Dto;
import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import com.test.musinsa.service.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandLowestPriceService extends AbstractService<Question2Dto> {

    private final MerchandiseRepository merchandiseRepository;
    private final CategoryRepository categoryRepository;


    @Override
    protected Question2Dto executeCore() {

        // DB에서 카테고리별 브랜드 최저가 데이터를 조회
        List<Object[]> lowestMerchandiseList = merchandiseRepository.findLowestPricePerCategoryByBrand();

        // 전체 카테고리 갯수를 조회
        int totalCategories = categoryRepository.findAll().size();

        return calculate(lowestMerchandiseList, totalCategories);
    }

    // 비지니스 로직
    private Question2Dto calculate(List<Object[]> lowestMerchandiseList, int totalCategories) {

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
    }
}
