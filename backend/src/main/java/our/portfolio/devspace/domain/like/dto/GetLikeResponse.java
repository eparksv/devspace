package our.portfolio.devspace.domain.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetLikeResponse {

    private Long id;

    private String name;

    private String image;

    private String job;

    private String company;

    public GetLikeResponse(Long id, String name, String image, String job, String company) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.job = job;
        this.company = company;
    }
}
