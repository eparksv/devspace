package our.portfolio.devspace.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public CustomException(ErrorDetail errorDetail) {
        super(errorDetail.getDescription());
        this.errorDetail = errorDetail;
    }
    public CustomException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
    }
}
