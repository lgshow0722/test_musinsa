package com.test.musinsa.api;

import com.test.musinsa.api.response.ApiResponse;
import com.test.musinsa.api.response.ResponseUtil;
import com.test.musinsa.dto.Question1Dto;
import com.test.musinsa.dto.Question2Dto;
import com.test.musinsa.dto.Question3Dto;
import com.test.musinsa.service.calcurate.CategoryBrandPriceReadService;
import com.test.musinsa.service.calcurate.BrandLowestPriceReadService;
import com.test.musinsa.service.calcurate.CategoryLowestPriceReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 과제 진행을 위한 컨트롤러
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final CategoryLowestPriceReadService categoryLowestPriceService;
    private final BrandLowestPriceReadService brandLowestPriceService;
    private final CategoryBrandPriceReadService categoryBrandPriceService;

    // 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    @GetMapping("/cate/lowest-price")
    public ResponseEntity<ApiResponse<Question1Dto>> cateLowestPrice() {

        Question1Dto result = categoryLowestPriceService.executeService();

        return ResponseUtil.successWithData(result);
    }

    // 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    @GetMapping("/brand/lowest-price")
    public ResponseEntity<ApiResponse<Question2Dto>> brandLowestPrice() {

        Question2Dto result = brandLowestPriceService.executeService();

        return ResponseUtil.successWithData(result);
    }

    // 3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    @GetMapping("/cate/brand-price")
    public ResponseEntity<ApiResponse<Question3Dto>> cateBrandPrice(@RequestParam String categoryName) {

        Question3Dto result = categoryBrandPriceService.executeService(categoryName);

        return ResponseUtil.successWithData(result);
    }

    // 4. 브랜드 추가 API
    @PostMapping("/brand/add")
    public ResponseEntity<ApiResponse<Void>> addBrand(@RequestParam String brandName) {
        return null;
    }

    // 5. 브랜드 수정 API

    // 6. 브랜드 삭제 API

    // 7. 상품 추가 API

    // 8. 상품 수정 API

    // 9. 상품 삭제 API
}
