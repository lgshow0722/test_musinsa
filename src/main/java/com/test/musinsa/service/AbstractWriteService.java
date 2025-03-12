package com.test.musinsa.service;

import jakarta.transaction.Transactional;

// 공통 추상 서비스 클래스(쓰기용)
public class AbstractWriteService<T, D> {

    /**
     * 등록(Create) 메서드
     *
     * @param dto 등록할 데이터(DTO)
     * @return 생성된 데이터의 엔티티
     */
    @Transactional
    public T create(D dto) {
        return null;
    }

    /**
     * 수정(Update) 메서드
     *
     * @param id  수정할 대상의 ID
     * @param dto 수정할 데이터(DTO)
     * @return 수정된 데이터의 엔티티
     */
    @Transactional
    public T update(int id, D dto) {
        return null;
    }

    /**
     * 삭제(Delete) 메서드
     *
     * @param id 삭제할 대상의 ID
     */
    @Transactional
    public void delete(int id) {

    }
}