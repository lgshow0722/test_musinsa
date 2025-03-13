package com.test.musinsa.service.transaction;

import com.test.musinsa.dto.base.BrandDto;
import com.test.musinsa.event.ActionType;
import com.test.musinsa.event.BrandEvent;
import com.test.musinsa.repository.BrandRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.service.AbstractWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandTransactionService extends AbstractWriteService<Brand, BrandDto> {

    private final BrandRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    protected void validate(BrandDto dto) {
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

        ActionType actionType = getCurrentActionType();

        eventPublisher.publishEvent(new BrandEvent(saveBrand, actionType));
        return saveBrand;
    }

    @Override
    protected Optional<Brand> findEntityById(Object id) {
        return repository.findById((Integer) id);
    }

    @Override
    protected Brand mergeEntity(Brand existingEntity, BrandDto dto) {
        existingEntity.setName(dto.getBrandName());
        return existingEntity;
    }

    @Override
    protected void deleteEntity(Brand entity) {
        repository.delete(entity);
    }
}
