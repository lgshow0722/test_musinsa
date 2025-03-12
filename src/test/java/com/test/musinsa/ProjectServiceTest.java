package com.test.musinsa;

import com.test.musinsa.dto.MerchandiseDto;
import com.test.musinsa.dto.Question1Dto;
import com.test.musinsa.dto.Question2Dto;
import com.test.musinsa.dto.Question3Dto;
import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.repository.entity.Category;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.repository.entity.MerchandiseRepository;
import com.test.musinsa.service.logic.BrandLowestPriceService;
import com.test.musinsa.service.logic.CategoryBrandPriceService;
import com.test.musinsa.service.logic.CategoryLowestPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @InjectMocks
    private CategoryLowestPriceService categoryLowestPriceService;

    @InjectMocks
    private BrandLowestPriceService brandLowestPriceService;

    @InjectMocks
    private CategoryBrandPriceService categoryBrandPriceService;

    private final NumberFormat format = NumberFormat.getNumberInstance();

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCategoryBrandLowestPrice() {

        // Given
        Merchandise merchandise1 = createMerchandise("Category1", "Brand1", 10000L);
        Merchandise merchandise2 = createMerchandise("Category2", "Brand2", 20000L);

        List<Merchandise> mockMerchandises = Arrays.asList(merchandise1, merchandise2);

        when(merchandiseRepository.findLowestPricePerCategory()).thenReturn(mockMerchandises);

        // When
        Question1Dto result = categoryLowestPriceService.executeService();

        // Then
        assertNotNull(result, "null이 아니어야 합니다");
        assertEquals(2, result.getMerchandiseDtoList().size(), "상품 목록의 크기가 2이어야 합니다.");
        assertEquals("10,000", result.getMerchandiseDtoList().get(0).getPrice(), "첫 번째 상품의 가격이 형식화된 10,000이어야 합니다.");
        assertEquals("30,000", result.getTotalPrice(), "총 가격은 30,000이어야 합니다.");

        // verify : 몇번 호출됬는지 검증
        verify(merchandiseRepository, times(1)).findLowestPricePerCategory();

    }

    @Test
    void testBrandLowestPrice() {
        // Given: Mock 데이터 설정
        List<Object[]> mockResults = List.of(
                new Object[]{"Category 1", "BrandA", 100L},
                new Object[]{"Category 2", "BrandA", 200L},
                new Object[]{"Category 1", "BrandB", 150L},
                new Object[]{"Category 2", "BrandB", 250L}
        );

        Mockito.when(merchandiseRepository.findLowestPricePerCategoryByBrand())
                .thenReturn(mockResults);

        Category category1 = new Category();
        category1.setName("Category 1");
        Category category2 = new Category();
        category2.setName("Category 2");
        List<Category> mockCategories = Arrays.asList(category1, category2);

        Mockito.when(categoryRepository.findAll())
                .thenReturn(mockCategories);

        // When: 테스트 대상 메서드 호출
        Question2Dto result = brandLowestPriceService.executeService();

        // Then: 반환 결과 검증
        assertNotNull(result);
        assertEquals("BrandA", result.getQuestion2Inner().getBrandName(), "최저가 브랜드 이름이 다릅니다.");
        assertEquals("300", result.getQuestion2Inner().getTotalPrice(), "최저가 브랜드의 총액이 잘못 계산되었습니다.");

        List<MerchandiseDto> merchandiseDtoList = result.getQuestion2Inner().getMerchandiseDtoList();
        assertEquals(2, merchandiseDtoList.size(), "MerchandiseDto 리스트의 크기가 다릅니다.");
        assertEquals("Category 1", merchandiseDtoList.get(0).getCategoryName());
        assertEquals("Category 2", merchandiseDtoList.get(1).getCategoryName());

    }

    @Test
    void testCategoryBrandPrice() {
        // Given
        String categoryName = "상의";
        Merchandise minPriceMerchandise = createMerchandise(categoryName,"BrandA",10000L);
        Merchandise maxPriceMerchandise = createMerchandise(categoryName,"BrandB",20000L);

        when(merchandiseRepository.findTopByCategory_NameOrderByPriceAsc(categoryName))
                .thenReturn(minPriceMerchandise);
        when(merchandiseRepository.findTopByCategory_NameOrderByPriceDesc(categoryName))
                .thenReturn(maxPriceMerchandise);

        // When
        Question3Dto result = categoryBrandPriceService.executeService(categoryName);

        // Then
        // 최저가 상품 검증
        assertEquals(categoryName, result.getCategoryName());
        assertEquals(1, result.getLowestPriceList().size());
        MerchandiseDto minBrand = result.getLowestPriceList().getFirst();
        assertEquals("BrandA", minBrand.getBrandName());
        assertEquals(format.format(10000), minBrand.getPrice());

        // 최고가 상품 검증
        assertEquals(1, result.getMaxPriceList().size());
        MerchandiseDto maxBrand = result.getMaxPriceList().getFirst();
        assertEquals("BrandB", maxBrand.getBrandName());
        assertEquals(format.format(20000), maxBrand.getPrice());

        verify(merchandiseRepository, times(1))
                .findTopByCategory_NameOrderByPriceAsc(categoryName);
        verify(merchandiseRepository, times(1))
                .findTopByCategory_NameOrderByPriceDesc(categoryName);

    }

    private Merchandise createMerchandise(String categoryName, String brandName, Long price) {
        Brand brand = new Brand();
        brand.setName(brandName);

        Category category = new Category();
        category.setName(categoryName);

        Merchandise merchandise = new Merchandise();
        merchandise.setPrice(price);
        merchandise.setBrand(brand);
        merchandise.setCategory(category);

        return merchandise;
    }

}
