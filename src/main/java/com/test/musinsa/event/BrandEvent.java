package com.test.musinsa.event;

import com.test.musinsa.repository.entity.Brand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrandEvent {

    public enum ActionType { CREATE, UPDATE, DELETE }

    private final Brand brand;
    private final ActionType actionType; // 동작 유형 (등록 or 수정)

}
