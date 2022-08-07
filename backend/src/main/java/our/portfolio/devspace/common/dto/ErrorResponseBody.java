package our.portfolio.devspace.common.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import our.portfolio.devspace.exception.ValidationError;

@Getter
@AllArgsConstructor
public class ErrorResponseBody {

    private final String message;
    private List<ValidationError> errors;

    public ErrorResponseBody(String message) {
        this.message = message;
    }

    public ErrorResponseBody(String message, BindingResult bindingResult) {
        this.message = message;
        this.errors = bindingResult.getFieldErrors()
            .stream()
            .map(error -> new ValidationError(
                error.getField(),
                error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                error.getDefaultMessage()))
            .collect(Collectors.toList());
    }
}
