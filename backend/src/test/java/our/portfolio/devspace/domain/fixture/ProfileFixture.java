package our.portfolio.devspace.domain.fixture;

import our.portfolio.devspace.domain.profile.entity.Profile;

public class ProfileFixture {
    public static final String NAME = "프로필 이름";
    public static final String INTRODUCTION = "프로필 소개";
    public static final String COMPANY_NAME = "회사 이름";

    public static Profile createProfile() {
        return Profile.builder()
            .user(UserFixture.createUser())
            .name(NAME)
            .introduction(INTRODUCTION)
            .company(COMPANY_NAME)
            .build();
    }
}
