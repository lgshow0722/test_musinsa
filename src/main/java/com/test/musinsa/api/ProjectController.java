package com.test.musinsa.api;

import com.test.musinsa.api.response.ApiResponse;
import com.test.musinsa.api.response.ResponseUtil;
import com.test.musinsa.dto.base.BrandDto;
import com.test.musinsa.dto.base.MerchandiseDto;
import com.test.musinsa.dto.input.BrandUpdateDto;
import com.test.musinsa.dto.input.MerchandiseUpdateDto;
import com.test.musinsa.dto.output.Question1Dto;
import com.test.musinsa.dto.output.Question2Dto;
import com.test.musinsa.dto.output.Question3Dto;
import com.test.musinsa.repository.entity.Brand;
import com.test.musinsa.repository.entity.Merchandise;
import com.test.musinsa.service.calcurate.CategoryBrandPriceReadService;
import com.test.musinsa.service.calcurate.BrandLowestPriceReadService;
import com.test.musinsa.service.calcurate.CategoryLowestPriceReadService;
import com.test.musinsa.service.transaction.BrandTransactionService;
import com.test.musinsa.service.transaction.MerchandiseTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final BrandTransactionService brandTransactionService;
    private final MerchandiseTransactionService merchandiseTransactionService;

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

        Brand brand = brandTransactionService.create(new BrandDto(brandName));

        if(brand != null) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST,"브랜드 추가에 실패했습니다.");
        }
    }

    // 5. 브랜드 수정 API
    @PutMapping("/brand/mod")
    public ResponseEntity<ApiResponse<Void>> modBrand(@RequestBody BrandUpdateDto dto) {

        Brand brand = brandTransactionService.update(dto.getId(), new BrandDto(dto.getBrandName()));

        if(brand != null) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST,"브랜드 수정에 실패했습니다.");
        }
    }

    // 6. 브랜드 삭제 API
    @DeleteMapping("/brand/del")
    public ResponseEntity<ApiResponse<Void>> delBrand(@RequestParam Integer id) {

        brandTransactionService.delete(id);

        return ResponseUtil.success(); // 로직 상 Exception이 발생할 경우 실패로 리턴
    }

    // 7. 상품 추가 API
    @PostMapping("/merchandise/add")
    public ResponseEntity<ApiResponse<Void>> addMerchandise(@RequestBody MerchandiseDto dto) {

        Merchandise merchandise = merchandiseTransactionService.create(dto);

        if(merchandise != null) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST,"상품 추가에 실패했습니다.");
        }
    }

    // 8. 상품 수정 API
    @PutMapping("/merchandise/mod")
    public ResponseEntity<ApiResponse<Void>> modMerchandise(@RequestBody MerchandiseUpdateDto dto) {

        Merchandise merchandise = merchandiseTransactionService.update(dto.getId(), new MerchandiseDto(dto.getCategoryId(), dto.getBrandId(), dto.getPrice()));

        if(merchandise != null) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST,"상품 수정에 실패했습니다.");
        }

    }

    // 9. 상품 삭제 API
}
