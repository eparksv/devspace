package our.portfolio.devspace.configuration.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class EnumConverterFactory implements ConverterFactory<String, Enum> {

    /**
     * String을 Enum으로 변환하는 메소드를 반환한다.
     *
     * @param targetType 변환 결과인 Enum 타입의 클래스
     * @return 변환된 Enum 상수
     */
    @Override
    @NonNull
    public <T extends Enum> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        return source -> {
            try {
                return (T) Enum.valueOf(targetType, source.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CustomException(String.format("%s에 %s가 존재하지 않습니다.", targetType.getSimpleName(), source), ErrorDetail.INVALID_INPUT_VALUE);
            }
        };
    }
}
