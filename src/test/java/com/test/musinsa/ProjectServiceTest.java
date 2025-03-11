package com.test.musinsa;

import com.test.musinsa.dto.CategoryBrandPriceDto;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.repository.entity.Category;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import com.test.musinsa.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCategoryBrandLowestPrice() {

        // Given
        Category category1 = new Category();
        category1.setName("Category1");
        Category category2 = new Category();
        category2.setName("Category2");

        Brand brand1 = new Brand();
        brand1.setName("Brand1");
        Brand brand2 = new Brand();
        brand2.setName("Brand2");

        Merchandise merchandise1 = new Merchandise();
        merchandise1.setCategory(category1);
        merchandise1.setBrand(brand1);
        merchandise1.setPrice(10000L);

        Merchandise merchandise2 = new Merchandise();
        merchandise2.setCategory(category2);
        merchandise2.setBrand(brand2);
        merchandise2.setPrice(20000L);

        List<Merchandise> mockMerchandises = Arrays.asList(merchandise1, merchandise2);

        when(merchandiseRepository.findLowestPricePerCategory()).thenReturn(mockMerchandises);

        // When
        CategoryBrandPriceDto result = projectService.getCategoryBrandLowestPrice();

        // Then
        assertNotNull(result, "null이 아니어야 합니다");
        assertEquals(2, result.getMerchandiseDtoList().size(), "상품 목록의 크기가 2이어야 합니다.");
        assertEquals("10,000", result.getMerchandiseDtoList().get(0).getPrice(), "첫 번째 상품의 가격이 형식화된 10,000이어야 합니다.");
        assertEquals("30,000", result.getTotalPrice(), "총 가격은 30,000이어야 합니다.");

        // verify : 몇번 호출됬는지 검증
        verify(merchandiseRepository, times(1)).findLowestPricePerCategory();

    }
}
