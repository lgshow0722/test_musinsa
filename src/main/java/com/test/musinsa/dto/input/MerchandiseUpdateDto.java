package com.test.musinsa.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchandiseUpdateDto {
    private int id;
    private int categoryId;
    private int brandId;
    private long price;
}
