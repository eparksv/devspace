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
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "Profile Not Found"),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "Job Not Found"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Entity Not Found"),
    INVALID_INPUT_VALUE(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Input Value"),
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "Posts Not Found"),
    PROFILE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Profile Already Exists"),
    INVALID_PARAMETER_VALUE(HttpStatus.BAD_REQUEST, "Invalid Parameter Value");

    private final HttpStatus status;
    private final String description;

}
