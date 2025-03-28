package com.test.musinsa.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question1Dto {

    @JsonProperty("카테고리별 데이터")
    private List<MerchandiseOutputDto> merchandiseOutputDtoList;

    @JsonProperty("총액")
    private String totalPrice;

}
