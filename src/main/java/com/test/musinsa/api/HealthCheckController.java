package com.test.musinsa.api;

import com.test.musinsa.api.response.ApiResponse;
import com.test.musinsa.api.response.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "헬스체크 컨트롤러")
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> health() {
        log.info("health check");
        return ResponseUtil.successWithMessage("Service is running");
    }
}
