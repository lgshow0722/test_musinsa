package com.test.musinsa.api.response;

public class ExceptionHandlerUtil {

    private static final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    /**
     * 공통 예외 처리 로직을 래핑하는 메서드
     * @param action 비즈니스 로직 실행
     * @param <T> 실행결과 타입
     * @return 비즈니스 로직 실행 결과
     */
    public static <T> T wrapWithExceptionHandling(ExceptionHandler<T> action) {
        try {
            return action.execute();
        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new IllegalArgumentException("데이터 처리에 실패했습니다.", e);
        }
    }

    // 커스텀 함수형 인터페이스
    @FunctionalInterface
    public interface ExceptionHandler<T> {
        T execute() throws Exception;
    }

}
