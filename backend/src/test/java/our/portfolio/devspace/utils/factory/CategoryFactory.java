package our.portfolio.devspace.utils.factory;

import lombok.Getter;
import lombok.Setter;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.category.entity.CategoryType;

import java.util.ArrayList;
import java.util.List;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

@Setter
@Getter
public class CategoryFactory {

    private Integer id;
    private String title = "카테고리";
    private CategoryType type = CategoryType.DEVELOP;

    public CategoryFactory(Integer id) {
        this.id = id;
    }

    public static List<CategoryResponse> categoryResponses(CategoryType type) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            CategoryFactory category = new CategoryFactory(i);
            category.setType(type);
            category.setTitle(type.toString() + i);
            categoryResponses.add(new CategoryResponse(i, category.getTitle()));
        }

        return categoryResponses;
    }

    public static List<Category> categoryEntities(CategoryType type) throws IllegalAccessException {
        List<Category> entities = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            CategoryFactory category = new CategoryFactory(i);
            category.setType(type);
            category.setTitle(type.toString() + i);
            entities.add(category.categoryEntity());
        }

        return entities;
    }

    public Category categoryEntity() throws IllegalAccessException {
        Category entity = new Category(this.title, this.type);

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }
}
