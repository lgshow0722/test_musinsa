package com.test.musinsa;

import com.test.musinsa.dto.base.BrandDto;
import com.test.musinsa.event.ActionType;
import com.test.musinsa.event.BrandEvent;
import com.test.musinsa.repository.BrandRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.service.transaction.BrandTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandTransactionTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private BrandTransactionService brandTransactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrandTest() {
        // Given
        BrandDto brandDto = new BrandDto("NewBrand");

        Brand brand = new Brand();
        brand.setName("NewBrand");

        when(brandRepository.existsByName("NewBrand")).thenReturn(false);
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        // When
        Brand createdBrand = brandTransactionService.create(brandDto);

        // Then
        assertNotNull(createdBrand);
        assertEquals("NewBrand", createdBrand.getName());

        // Verify repository interactions
        verify(brandRepository, times(1)).existsByName("NewBrand");
        verify(brandRepository, times(1)).save(any(Brand.class));

        // Verify event publishing
        ArgumentCaptor<BrandEvent> eventCaptor = ArgumentCaptor.forClass(BrandEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.CREATE, eventCaptor.getValue().getActionType());
    }

    @Test
    void updateBrandTest() {
        // Given
        BrandDto brandDto = new BrandDto("UpdatedBrand");

        Brand existingBrand = new Brand();
        existingBrand.setId(1);
        existingBrand.setName("ExistingBrand");

        when(brandRepository.findById(1)).thenReturn(Optional.of(existingBrand));
        when(brandRepository.existsByName("UpdatedBrand")).thenReturn(false);
        when(brandRepository.save(any(Brand.class))).thenReturn(existingBrand);

        // When
        Brand updatedBrand = brandTransactionService.update(1, brandDto);

        // Then
        assertNotNull(updatedBrand);
        assertEquals("UpdatedBrand", updatedBrand.getName());

        // Verify repository interactions
        verify(brandRepository, times(1)).findById(1);
        verify(brandRepository, times(1)).existsByName("UpdatedBrand");
        verify(brandRepository, times(1)).save(any(Brand.class));

        // Verify event publishing
        ArgumentCaptor<BrandEvent> eventCaptor = ArgumentCaptor.forClass(BrandEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.UPDATE, eventCaptor.getValue().getActionType());
    }

    @Test
    void deleteBrandTest() {
        // Given
        Brand existingBrand = new Brand();
        existingBrand.setId(1);
        existingBrand.setName("BrandToDelete");

        when(brandRepository.findById(1)).thenReturn(Optional.of(existingBrand));

        // When
        brandTransactionService.delete(1);

        // Then
        // Verify repository interactions
        verify(brandRepository, times(1)).findById(1);
        verify(brandRepository, times(1)).delete(any(Brand.class));

        // Verify event publishing
        ArgumentCaptor<BrandEvent> eventCaptor = ArgumentCaptor.forClass(BrandEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.DELETE, eventCaptor.getValue().getActionType());
    }


}
