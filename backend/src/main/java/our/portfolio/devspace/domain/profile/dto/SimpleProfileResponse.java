package our.portfolio.devspace.domain.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleProfileResponse {

    private final long id;
    private final String name;
    private final String job;
    private final String company;
    private final String image;

    @Builder
    public SimpleProfileResponse(long id, String name, String job, String company, String image) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.company = company;
        this.image = image;
    }
}
