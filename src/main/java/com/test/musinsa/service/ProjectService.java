package com.test.musinsa.service;

import com.test.musinsa.dto.CategoryBrandPriceDto;
import com.test.musinsa.dto.MerchandiseDto;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final MerchandiseRepository merchandiseRepository;

    /**
     * 카테고리별 브랜드 최저가 API
     * @return CategoryBrandPriceDto
     * @throws Exception 오류 시 예외 발생
     */
    public CategoryBrandPriceDto getCategoryBrandLowestPrice() {
        try {
            // 각 카테고리별 최저가 상품을 DB에서 가져옴 ( nativeQuery 사용 )
            List<Merchandise> lowestPriceMerchandises = merchandiseRepository.findLowestPricePerCategory();

            // 숫자 포매터 적용
            NumberFormat format = NumberFormat.getNumberInstance();

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

            // CategoryBrandPriceDto에 저장하여 리턴
            return new CategoryBrandPriceDto(merchandiseDtoList, format.format(totalPrice));
        } catch (Exception e) {
            throw new IllegalArgumentException("상품 정보 조회에 실패했습니다.");
        }
    }
}
