package com.test.musinsa.service;

import com.test.musinsa.api.response.ExceptionHandlerUtil;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;

// 공통 추상 서비스 클래스(조회용)
public abstract class AbstractReadService<T> {

    // 숫자 포매터
    protected final NumberFormat format = NumberFormat.getNumberInstance();

    // 공통 로직: 예외 처리 래핑
    @Transactional(readOnly = true)
    public T executeService(Object... params) {
        return ExceptionHandlerUtil.wrapWithExceptionHandling(() -> executeCore(params));
    }

    // 하위 클래스가 구현해야 할 비즈니스 로직
    protected abstract T executeCore(Object... params);

}
