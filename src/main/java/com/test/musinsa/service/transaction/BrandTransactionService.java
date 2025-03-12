package com.test.musinsa.service.transaction;

import com.test.musinsa.dto.BrandDto;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.service.AbstractWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandTransactionService extends AbstractWriteService<Brand, BrandDto> {
}
