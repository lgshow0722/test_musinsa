package com.test.musinsa.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchandiseDto {
    private int categoryId;
    private int brandId;
    private long price;
}
