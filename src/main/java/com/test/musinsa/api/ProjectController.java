package com.test.musinsa.api;

import com.test.musinsa.api.response.ApiResponse;
import com.test.musinsa.api.response.ResponseUtil;
import com.test.musinsa.dto.CategoryBrandPriceDto;
import com.test.musinsa.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 과제 진행을 위한 컨트롤러
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    @GetMapping("/cate/lowest-price")
    public ResponseEntity<ApiResponse<CategoryBrandPriceDto>> cateLowestPrice() {

        CategoryBrandPriceDto result = projectService.getCategoryBrandLowestPrice();

        return ResponseUtil.successWithData(result);
    }

    // 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API

    // 3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API

    // 4. 브랜드 추가 API

    // 5. 브랜드 수정 API

    // 6. 브랜드 삭제 API

    // 7. 상품 추가 API

    // 8. 상품 수정 API

    // 9. 상품 삭제 API
}
