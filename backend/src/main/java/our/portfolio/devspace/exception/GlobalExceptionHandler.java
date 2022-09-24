package our.portfolio.devspace.exception;

import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import our.portfolio.devspace.common.dto.ErrorResponseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // HTTP Status Code: 422 Unprocessable Entity, Response Body: { message, errors: ValidationError}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponseBody body = new ErrorResponseBody(ErrorDetail.VALIDATION_FAILED.getDescription(), e.getBindingResult());
        return new ResponseEntity<>(body, ErrorDetail.VALIDATION_FAILED.getStatus());
    }

    // 컨트롤러 @ModelAttribute의 바인딩이 실패했을 경우 발생한다.
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponseBody> handleBindException(BindException e) {
        ErrorResponseBody body = new ErrorResponseBody(ErrorDetail.INVALID_INPUT_VALUE.getDescription(), e.getBindingResult());
        return new ResponseEntity<>(body, ErrorDetail.INVALID_INPUT_VALUE.getStatus());
    }

    // Enum 타입의 바인딩이 실패했을 경우 발생한다.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponseBody> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorResponseBody body = new ErrorResponseBody(Objects.requireNonNull(e.getRootCause()).getMessage());
        return new ResponseEntity<>(body, ErrorDetail.INVALID_INPUT_VALUE.getStatus());
    }


    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseBody> handleCustomException(CustomException e) {
        ErrorResponseBody body = new ErrorResponseBody(e.getMessage());
        return new ResponseEntity<>(body, e.getErrorDetail().getStatus());
    }

    // HTTP Status Code: 500 Internal Server Error, Response Body: { message }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseBody> handleOtherException() {
        ErrorResponseBody body = new ErrorResponseBody("서버 에러로 요청에 응답할 수 없습니다.");
        return new ResponseEntity<>(body, ErrorDetail.INTERNAL_SERVER_ERROR.getStatus());
    }
}
