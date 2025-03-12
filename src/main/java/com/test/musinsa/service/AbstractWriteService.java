package com.test.musinsa.service;

import jakarta.transaction.Transactional;

// 공통 추상 서비스 클래스(쓰기용)
public abstract class AbstractWriteService<T, D> {

    /**
     * 등록(Create) 메서드
     *
     * @param dto 등록할 데이터(DTO)
     * @return 생성된 데이터의 엔티티
     */
    @Transactional
    public T create(D dto) {
        // DTO 검증
        validateForCreate(dto);

        // DTO -> 엔티티 변환
        T entity = convertToEntity(dto);

        // 엔티티 저장
        return saveEntity(entity);

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

    /**
     * 데이터 등록 시의 검증 로직
     */
    protected void validateForCreate(D dto) {
        // 기본 구현 없음. 필요할 경우 하위 클래스에서 추가 구현.
    }

    /**
     * DTO 데이터를 기반으로 엔티티를 생성
     * 하위 클래스에서 구현 필요
     */
    protected abstract T convertToEntity(D dto);

    /**
     * 엔티티 저장 로직을 구현
     * 하위 클래스에서 구현 필요
     */
    protected abstract T saveEntity(T entity);



}