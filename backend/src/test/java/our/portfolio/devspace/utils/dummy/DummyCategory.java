package our.portfolio.devspace.utils.dummy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DummyCategory {

    private Integer id;
    private String title = "카테고리";

    public DummyCategory(Integer id) {
        this.id = id;
    }
}
