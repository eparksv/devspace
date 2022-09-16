package our.portfolio.devspace.utils.factory;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

import lombok.Getter;
import lombok.Setter;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.category.entity.CategoryType;

@Setter
@Getter
public class CategoryFactory {

    private Integer id;
    private String title = "카테고리";
    private CategoryType type = CategoryType.DEVELOP;

    public CategoryFactory(Integer id) {
        this.id = id;
    }

    public Category categoryEntity() throws IllegalAccessException {
        Category entity = new Category(this.title, this.type);

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }
}
