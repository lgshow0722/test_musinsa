package com.test.musinsa.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question2Dto {

    @JsonProperty("최저가")
    private Question2Inner question2Inner;

    @Data
    @AllArgsConstructor
    public static class Question2Inner {

        @JsonProperty("브랜드")
        private String brandName;

        @JsonProperty("카테고리")
        private List<MerchandiseOutputDto> merchandiseOutputDtoList;

        @JsonProperty("총액")
        private String totalPrice;
    }
}
