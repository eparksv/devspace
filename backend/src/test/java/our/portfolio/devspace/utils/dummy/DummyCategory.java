package our.portfolio.devspace.utils.dummy;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

import lombok.Getter;
import lombok.Setter;
import our.portfolio.devspace.domain.category.entity.Category;

@Setter
@Getter
public class DummyCategory {

    private Integer id;
    private String title = "카테고리";

    public DummyCategory(Integer id) {
        this.id = id;
    }

    public Category categoryEntity() throws IllegalAccessException {
        Category entity = new Category(this.title);

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }
}
