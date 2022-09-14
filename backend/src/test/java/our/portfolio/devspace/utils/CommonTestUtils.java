package our.portfolio.devspace.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.reflect.FieldUtils;

public class CommonTestUtils {

    public static String valueToString(Object value) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(value);
    }

    public static void setIdField(Object object, Number id) throws IllegalAccessException {
        FieldUtils.writeField(object, "id", id, true);
    }
}
