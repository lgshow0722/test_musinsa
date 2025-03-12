package com.test.musinsa.event;

import com.test.musinsa.repository.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BrandEventListener {

    @EventListener
    public void onBrandAdded(Brand event) {
        log.info("브랜드 추가 이벤트 수신: 브랜드 ID = {}, 브랜드 이름 = {}",
                event.getId(), event.getName());
        // 다른 비즈니스 로직을 처리하거나 추가 작업
    }

}
