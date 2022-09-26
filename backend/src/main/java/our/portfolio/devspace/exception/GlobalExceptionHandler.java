package our.portfolio.devspace.exception;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import our.portfolio.devspace.common.dto.ErrorResponseBody;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * DTO의 Validation이 실패할 때 발생하는 예외를 처리한다.
     *
     * @param e 발생한 {@link MethodArgumentNotValidException}
     * @return 에러 메시지와 바인딩 결과를 담은 {@link ErrorResponseBody}, Status 422 UNPROCESSABLE ENTITY
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(e.getMessage(), e);
        ErrorResponseBody body = new ErrorResponseBody(ErrorDetail.VALIDATION_FAILED.getDescription(), e.getBindingResult());
        return new ResponseEntity<>(body, ErrorDetail.VALIDATION_FAILED.getStatus());
    }

    /**
     * 컨트롤러 @ModelAttribute의 바인딩이 실패했을 경우 발생하는 예외를 처리한다.
     *
     * @param e 발생한 {@link BindException}
     * @return 에러 메시지와 바인딩 결과를 담은 {@link ErrorResponseBody}, Status 422 UNPROCESSABLE ENTITY
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponseBody> handleBindException(BindException e) {
        log.info(e.getMessage(), e);
        ErrorResponseBody body = new ErrorResponseBody(ErrorDetail.INVALID_INPUT_VALUE.getDescription(), e.getBindingResult());
        return new ResponseEntity<>(body, ErrorDetail.INVALID_INPUT_VALUE.getStatus());
    }

    /**
     * Enum 타입의 바인딩이 실패했을 경우 발생하는 예외를 처리한다.
     *
     * @param e 발생한 {@link MethodArgumentTypeMismatchException}
     * @return 에러 메시지를 담은 {@link ErrorResponseBody}, Status 422 UNPROCESSABLE ENTITY
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponseBody> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info(e.getMessage(), e);
        ErrorResponseBody body = new ErrorResponseBody(Objects.requireNonNull(e.getRootCause()).getMessage());
        return new ResponseEntity<>(body, ErrorDetail.INVALID_INPUT_VALUE.getStatus());
    }


    /**
     * 비즈니스 예외를 처리한다.
     *
     * @param e 발생한 {@link CustomException}
     * @return 에러 메시지를 담은 {@link ErrorResponseBody}, Status는 {@link ErrorDetail}로 지정된 상태코드
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseBody> handleCustomException(CustomException e) {
        log.info(e.getMessage(), e);
        ErrorResponseBody body = new ErrorResponseBody(e.getMessage());
        return new ResponseEntity<>(body, e.getErrorDetail().getStatus());
    }

    /**
     * 그 외에 발생하는 예외를 처리한다.
     *
     * @param e 발생한 {@link Exception}
     * @return 에러 메시지를 담은 {@link ErrorResponseBody}, Status 500 INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseBody> handleOtherException(Exception e) {
        log.error(e.getMessage(), e);
        ErrorResponseBody body = new ErrorResponseBody("서버 에러로 요청에 응답할 수 없습니다.");
        return new ResponseEntity<>(body, ErrorDetail.INTERNAL_SERVER_ERROR.getStatus());
    }
}
