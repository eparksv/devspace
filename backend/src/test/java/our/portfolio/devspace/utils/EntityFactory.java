package our.portfolio.devspace.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

public class EntityFactory {

    public static void setIdField(Object object, Number id) throws IllegalAccessException {
        FieldUtils.writeField(object, "id", id, true);
    }
}
