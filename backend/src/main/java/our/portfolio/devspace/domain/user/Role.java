package our.portfolio.devspace.domain.user;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    GUEST("GUEST");

    private final String code;

    public static Role from(String code) {
        return Arrays.stream(Role.values())
            .filter(r -> r.getCode().equals(code))
            .findAny()
            .orElse(GUEST);
    }
}
