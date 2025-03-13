package com.test.musinsa.event;

import com.test.musinsa.repository.entity.Merchandise;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MerchandiseEvent {

    private final Merchandise merchandise;
    private final ActionType actionType;

}
