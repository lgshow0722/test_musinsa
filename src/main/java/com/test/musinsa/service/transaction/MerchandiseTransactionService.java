package com.test.musinsa.service.transaction;

import com.test.musinsa.dto.base.MerchandiseDto;
import com.test.musinsa.event.ActionType;
import com.test.musinsa.event.MerchandiseEvent;
import com.test.musinsa.repository.BrandRepository;
import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.MerchandiseRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.repository.entity.Category;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.service.AbstractWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchandiseTransactionService extends AbstractWriteService<Merchandise, MerchandiseDto> {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    protected void validate(MerchandiseDto dto) {
        // 예외 처리 : 카테고리 유무
        Category category = getCategoryById(dto.getCategoryId());
        // 예외 처리 : 브랜드 유무
        Brand brand = getBrandById(dto.getBrandId());
    }

    @Override
    protected Merchandise convertToEntity(MerchandiseDto dto) {
        Category category = getCategoryById(dto.getCategoryId());
        Brand brand = getBrandById(dto.getBrandId());

        Merchandise merchandise = new Merchandise();
        merchandise.setCategory(category);
        merchandise.setBrand(brand);
        merchandise.setPrice(dto.getPrice());

        return merchandise;
    }

    @Override
    protected Merchandise saveEntity(Merchandise entity) {
        Merchandise saveMerchandise = merchandiseRepository.save(entity);

        ActionType actionType = getCurrentActionType();

        eventPublisher.publishEvent(new MerchandiseEvent(saveMerchandise, actionType));
        return saveMerchandise;
    }

    @Override
    protected Optional<Merchandise> findEntityById(Object id) {
        return Optional.empty();
    }

    @Override
    protected Merchandise mergeEntity(Merchandise existingEntity, MerchandiseDto dto) {
        return null;
    }

    @Override
    protected void deleteEntity(Merchandise entity) {

    }

    private Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("카테고리를 찾을 수 없습니다: " + categoryId));
    }

    private Brand getBrandById(int brandId) {
        return brandRepository.findById(brandId).orElseThrow(() ->
                new IllegalArgumentException("브랜드를 찾을 수 없습니다: " + brandId));
    }

}
