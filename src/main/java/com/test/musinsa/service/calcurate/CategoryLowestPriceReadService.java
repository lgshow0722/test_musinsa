package com.test.musinsa.service.calcurate;

import com.test.musinsa.dto.output.MerchandiseOutputDto;
import com.test.musinsa.dto.output.Question1Dto;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.MerchandiseRepository;
import com.test.musinsa.service.AbstractReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryLowestPriceReadService extends AbstractReadService<Question1Dto> {

    private final MerchandiseRepository merchandiseRepository;

    @Override
    protected Question1Dto executeCore(Object... params) {
        // 각 카테고리별 최저가 상품을 DB에서 가져옴 ( nativeQuery 사용 )
        List<Merchandise> lowestPriceMerchandises = merchandiseRepository.findLowestPricePerCategory();

        return calculate(lowestPriceMerchandises);
    }

    // 비지니스 로직
    private Question1Dto calculate(List<Merchandise> lowestPriceMerchandises) {
        // 총액이 추가로 필요하여 stream으로 처리함
        List<MerchandiseOutputDto> merchandiseOutputDtoList = new ArrayList<>();
        long totalPrice = lowestPriceMerchandises.stream()
                .map(m -> {
                    // Merchandise -> MerchandiseDto로 변환하여 merchandiseDtoList에 추가
                    MerchandiseOutputDto dto = new MerchandiseOutputDto(
                            m.getCategory().getName(),
                            m.getBrand().getName(),
                            format.format(m.getPrice())
                    );
                    merchandiseOutputDtoList.add(dto);
                    return m.getPrice();
                })
                .mapToLong(Long::longValue)
                .sum(); // 총액

        // Question1Dto 저장하여 리턴
        return new Question1Dto(merchandiseOutputDtoList, format.format(totalPrice));
    }
}
