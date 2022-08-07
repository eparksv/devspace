package our.portfolio.devspace.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorDetail {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, "Validation Failed"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "Job Not Found");

    private final HttpStatus status;
    private final String description;

}
