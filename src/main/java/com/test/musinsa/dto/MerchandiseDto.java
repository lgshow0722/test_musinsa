package com.test.musinsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchandiseDto {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("가격")
    private String price;
}
