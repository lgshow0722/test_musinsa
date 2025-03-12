package com.test.musinsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandDto {

    @JsonProperty("브랜드")
    private String brandName;

}
