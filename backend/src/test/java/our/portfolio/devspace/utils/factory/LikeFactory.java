package our.portfolio.devspace.utils.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import our.portfolio.devspace.domain.category.entity.CategoryType;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;

import java.util.ArrayList;
import java.util.List;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

@Setter
@Getter
public class LikeFactory {

    private Long id;

    private String name = "이름";

    private String image = "사진";

    private String job = "직업";

    private String company = "회사";

    private CategoryType type = CategoryType.DEVELOP;

    public LikeFactory(Long id) {
        this.id = id;
    }

    public static List<GetLikeResponse> GetLikeResponses(Pageable pageable) {
        List<GetLikeResponse> getLikeResponses = new ArrayList<>();

        for (Long i = 1L; i <= pageable.getPageSize(); i++) {
            LikeFactory like = new LikeFactory(i);
            like.setName("이름" + i);
            like.setImage("사진" + i);
            like.setJob("직업" + i);
            like.setCompany("회사" + i);
            getLikeResponses.add(new GetLikeResponse(i, like.getName(), like.getImage(), like.getJob(), like.getCompany()));
        }

        return getLikeResponses;
    }

    public static List<GetLikeResponse> LikeDtos(Long id, Pageable pageable) throws IllegalAccessException {
        List<GetLikeResponse> getLikeResponses = new ArrayList<>();

        for (Long i = 1L; i <= pageable.getPageSize(); i++) {
            LikeFactory like = new LikeFactory(i);
            like.setName("이름" + i);
            like.setImage("사진" + i);
            like.setJob("직업" + i);
            like.setCompany("회사" + i);
            getLikeResponses.add(like.LikeDto());
        }

        return getLikeResponses;
    }

    public GetLikeResponse LikeDto() throws IllegalAccessException {
        GetLikeResponse dto = new GetLikeResponse(this.id, this.name, this.image, this.job, this.company);

        if (this.id != null) {
            setIdField(dto, this.id);
        }

        return dto;
    }
}
