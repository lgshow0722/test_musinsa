package com.test.musinsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question3Dto {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("최저가")
    private List<MerchandiseDto> lowestPriceList;

    @JsonProperty("최고가")
    private List<MerchandiseDto> maxPriceList;
}
