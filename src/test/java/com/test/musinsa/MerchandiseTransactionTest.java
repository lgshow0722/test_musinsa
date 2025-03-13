package com.test.musinsa;

import com.test.musinsa.dto.base.MerchandiseDto;
import com.test.musinsa.event.ActionType;
import com.test.musinsa.event.MerchandiseEvent;
import com.test.musinsa.repository.BrandRepository;
import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.MerchandiseRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.repository.entity.Category;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.service.transaction.MerchandiseTransactionService;
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

public class MerchandiseTransactionTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private MerchandiseTransactionService merchandiseTransactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMerchandiseTest() {
        // Given
        MerchandiseDto dto = new MerchandiseDto(1, 2, 10000L);

        Category category = new Category();
        category.setId(1);

        Brand brand = new Brand();
        brand.setId(2);

        Merchandise merchandise = new Merchandise();
        merchandise.setId(1);
        merchandise.setPrice(10000L);
        merchandise.setCategory(category);
        merchandise.setBrand(brand);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(brandRepository.findById(2)).thenReturn(Optional.of(brand));
        when(merchandiseRepository.save(any(Merchandise.class))).thenReturn(merchandise);

        // When
        Merchandise createdMerchandise = merchandiseTransactionService.create(dto);

        // Then
        assertNotNull(createdMerchandise);
        assertEquals(10000L, createdMerchandise.getPrice());
        assertEquals(1, createdMerchandise.getCategory().getId());
        assertEquals(2, createdMerchandise.getBrand().getId());

        // Verify repository and event calls
        verify(categoryRepository, times(2)).findById(1);
        verify(brandRepository, times(2)).findById(2);
        verify(merchandiseRepository, times(1)).save(any(Merchandise.class));

        ArgumentCaptor<MerchandiseEvent> eventCaptor = ArgumentCaptor.forClass(MerchandiseEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.CREATE, eventCaptor.getValue().getActionType());
    }

    @Test
    void updateMerchandiseTest() {
        // Given
        MerchandiseDto dto = new MerchandiseDto(1, 2, 15000L);

        Category category = new Category();
        category.setId(1);

        Brand brand = new Brand();
        brand.setId(2);

        Merchandise existingMerchandise = new Merchandise();
        existingMerchandise.setId(1);
        existingMerchandise.setPrice(10000L);

        when(merchandiseRepository.findById(1)).thenReturn(Optional.of(existingMerchandise));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(brandRepository.findById(2)).thenReturn(Optional.of(brand));
        when(merchandiseRepository.save(any(Merchandise.class))).thenReturn(existingMerchandise);

        // When
        Merchandise updatedMerchandise = merchandiseTransactionService.update(1, dto);

        // Then
        assertNotNull(updatedMerchandise);
        assertEquals(15000L, updatedMerchandise.getPrice());
        assertEquals(1, updatedMerchandise.getCategory().getId());
        assertEquals(2, updatedMerchandise.getBrand().getId());

        // Verify repository and event calls
        verify(merchandiseRepository, times(1)).findById(1);
        verify(categoryRepository, times(2)).findById(1);
        verify(brandRepository, times(2)).findById(2);
        verify(merchandiseRepository, times(1)).save(any(Merchandise.class));

        ArgumentCaptor<MerchandiseEvent> eventCaptor = ArgumentCaptor.forClass(MerchandiseEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.UPDATE, eventCaptor.getValue().getActionType());
    }

    @Test
    void deleteMerchandiseTest() {
        // Given
        Merchandise existingMerchandise = new Merchandise();
        existingMerchandise.setId(1);

        when(merchandiseRepository.findById(1)).thenReturn(Optional.of(existingMerchandise));

        // When
        merchandiseTransactionService.delete(1);

        // Then
        // Verify repository and event calls
        verify(merchandiseRepository, times(1)).findById(1);
        verify(merchandiseRepository, times(1)).delete(existingMerchandise);

        ArgumentCaptor<MerchandiseEvent> eventCaptor = ArgumentCaptor.forClass(MerchandiseEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());
        assertEquals(ActionType.DELETE, eventCaptor.getValue().getActionType());
    }


}
