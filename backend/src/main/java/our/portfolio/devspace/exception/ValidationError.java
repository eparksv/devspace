package our.portfolio.devspace.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {

    private final String field;
    private final String value;
    private final String reason;

}
