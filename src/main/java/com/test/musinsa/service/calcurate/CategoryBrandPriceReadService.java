package com.test.musinsa.service.calcurate;

import com.test.musinsa.dto.MerchandiseDto;
import com.test.musinsa.dto.Question3Dto;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import com.test.musinsa.service.AbstractReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryBrandPriceReadService extends AbstractReadService<Question3Dto> {

    private final MerchandiseRepository merchandiseRepository;

    @Override
    protected Question3Dto executeCore(Object... params) {
        String categoryName = (String) params[0];

        // 최저가 브랜드 조회
        Merchandise minPriceMerchandise = merchandiseRepository.findTopByCategory_NameOrderByPriceAsc(categoryName);
        MerchandiseDto minPriceBrand = new MerchandiseDto(
                null,
                minPriceMerchandise.getBrand().getName(),
                format.format(minPriceMerchandise.getPrice())
        );

        // 최고가 브랜드 조회
        Merchandise maxPriceMerchandise = merchandiseRepository.findTopByCategory_NameOrderByPriceDesc(categoryName);
        MerchandiseDto maxPriceBrand = new MerchandiseDto(
                null,
                maxPriceMerchandise.getBrand().getName(),
                format.format(maxPriceMerchandise.getPrice())
        );

        return new Question3Dto(categoryName, List.of(minPriceBrand), List.of(maxPriceBrand));
    }
}
