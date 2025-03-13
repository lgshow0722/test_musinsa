package com.test.musinsa.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 항목 출력하지 않음
public class MerchandiseOutputDto {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("가격")
    private String price;
}
