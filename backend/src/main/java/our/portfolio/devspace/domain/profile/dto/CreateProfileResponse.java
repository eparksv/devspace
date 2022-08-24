package our.portfolio.devspace.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Getter
@AllArgsConstructor
public class CreateProfileResponse {

    private final Long id;

    public static CreateProfileResponse from(Profile profile) {
        return new CreateProfileResponse(profile.getId());
    }
}