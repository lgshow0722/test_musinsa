package com.test.musinsa.service.transaction;

import com.test.musinsa.dto.BrandDto;
import com.test.musinsa.repository.BrandRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.service.AbstractWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandTransactionService extends AbstractWriteService<Brand, BrandDto> {

    private final BrandRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    protected void validateForCreate(BrandDto dto) {
        // 예외 처리 : 브랜드 이름 중복
        if(repository.existsByName(dto.getBrandName())) {
            throw new IllegalArgumentException("이미 존재하는 브랜드 이름입니다: " + dto.getBrandName());
        }
    }

    @Override
    protected Brand convertToEntity(BrandDto dto) {
        Brand brand = new Brand();
        brand.setName(dto.getBrandName());
        return brand;
    }

    @Override
    protected Brand saveEntity(Brand entity) {
        Brand saveBrand = repository.save(entity);
        eventPublisher.publishEvent(saveBrand);
        return saveBrand;
    }
}
