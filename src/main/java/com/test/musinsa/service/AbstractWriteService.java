package com.test.musinsa.service;

import com.test.musinsa.event.ActionType;
import jakarta.transaction.Transactional;

import java.util.Optional;

// 공통 추상 서비스 클래스(쓰기용)
public abstract class AbstractWriteService<T, D> {

    // 액션 유형(등록/수정)
    private ActionType currentActionType;

    // 현재 동작 타입 Getter
    protected ActionType getCurrentActionType() {
        return currentActionType;
    }

    // 각 메서드에서 동작 타입 설정
    protected void setCurrentActionType(ActionType actionType) {
        this.currentActionType = actionType;
    }

    /**
     * 등록(Create) 메서드
     *
     * @param dto 등록할 데이터(DTO)
     * @return 생성된 데이터의 엔티티
     */
    @Transactional
    public T create(D dto) {

        setCurrentActionType(ActionType.CREATE); // 등록 작업 세팅

        // DTO 검증
        validate(dto);

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

        setCurrentActionType(ActionType.UPDATE); // 수정 작업 세팅

        // 기존 엔티티를 조회
        T existingEntity = findEntityById(id).orElseThrow(() ->
                new IllegalArgumentException("엔티티를 찾을 수 없습니다 : " + id));

        // DTO 검증
        validate(dto);

        // DTO 데이터를 기존 엔티티에 병합
        T updatedEntity = mergeEntity(existingEntity, dto);

        // 갱신된 엔티티 저장
        return saveEntity(updatedEntity);

    }

    /**
     * 삭제(Delete) 메서드
     *
     * @param id 삭제할 대상의 ID
     */
    @Transactional
    public void delete(int id) {

        setCurrentActionType(ActionType.DELETE); // 삭제 작업 세팅

        // 기존 엔티티를 조회
        T entity = findEntityById(id).orElseThrow(() ->
                new IllegalArgumentException("엔티티를 찾을 수 없습니다 : " + id));

        // 엔티티 삭제 수행
        deleteEntity(entity);

    }

    /**
     * 데이터 등록 시의 검증 로직 ( 등록/수정 을 하나의 메서드로 처리한다. 필요 시 분리 )
     */
    protected void validate(D dto) {
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

    /**
     * 주어진 ID로 엔티티를 찾음 (Optional로 반환)
     * 하위 클래스에서 구현 필요
     */
    protected abstract Optional<T> findEntityById(Object id);

    /**
     * 기존 엔티티와 DTO를 병합하여 수정된 엔티티를 반환
     * 하위 클래스에서 구현 필요
     * @param existingEntity 기존 엔티티
     * @param dto 갱신에 사용할 DTO
     * @return 병합된 엔티티
     */
    protected abstract T mergeEntity(T existingEntity, D dto);

    /**
     * 하위 클래스에서 구현될 엔티티 삭제 추상 메서드
     */
    protected abstract void deleteEntity(T entity);

}