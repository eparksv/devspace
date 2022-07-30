package our.portfolio.devspace.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpResponseBody<T> {

    private final String message;
    private final T data;

}
