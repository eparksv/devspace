package our.portfolio.devspace.domain.profile.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Getter
public class ProfileCreationDto {

    @NotNull(message = "이름을 입력하세요.")
    @Length(min = 2, max = 12, message = "이름은 12자 이하로 입력하세요.")
    private final String name;

    @Length(max = 16383, message = "자기소개는 16,383자 이하로 입력하세요.") // MYSQL TEXT 타입 최대 65,535 byte / 4 (utf-8 한글자당 최대 4byte)
    private final String introduction;

    @NotNull
    private final Integer jobId;

    @Builder
    public ProfileCreationDto(String name, String introduction, Integer jobId) {
        this.name = name;
        this.introduction = introduction;
        this.jobId = jobId;
    }

    public static ProfileCreationDto from(Profile entity) {
        return new ProfileCreationDto(entity.getName(), entity.getIntroduction(), entity.getJob().getId());
    }
}
