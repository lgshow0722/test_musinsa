package com.test.musinsa.event;

import com.test.musinsa.repository.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BrandEventListener {

    @EventListener
    public void onBrandEvent(BrandEvent event) {
        log.info("브랜드 이벤트 수신: 브랜드 ID = {}, 브랜드 이름 = {}, 액션 유형 = {}",
                event.getBrand().getId(), event.getBrand().getName(), event.getActionType());
        switch (event.getActionType()) {
            case CREATE -> {
                // 등록 관련 이벤트
                log.info("브랜드 등록 이벤트 발생!");
            }
            case UPDATE -> {
                // 수정 관련 이벤트
                log.info("브랜드 수정 이벤트 발생!");
            }
            case DELETE -> {
                // 삭제 관련 이벤트
                log.info("브랜드 삭제 이벤트 발생!");
            }
        }
    }

}
