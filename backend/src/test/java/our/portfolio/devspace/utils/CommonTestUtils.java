package our.portfolio.devspace.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTestUtils {

    public static String valueToString(Object value) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(value);
    }

}
