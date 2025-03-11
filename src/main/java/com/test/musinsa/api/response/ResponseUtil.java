package com.test.musinsa.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    // 성공 응답 생성
    public static <T> ResponseEntity<ApiResponse<Void>> success() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success"));
    }

    // 성공 응답 생성 (데이터만)
    public static <T> ResponseEntity<ApiResponse<T>> successWithData(T data) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), null, data));
    }

    // 성공 응답 (데이터 없이 메시지만)
    public static ResponseEntity<ApiResponse<Void>> successWithMessage(String message) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), message));
    }

    // 실패 응답 생성
    public static ResponseEntity<ApiResponse<Void>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ApiResponse<>(status.value(), message));
    }
}
