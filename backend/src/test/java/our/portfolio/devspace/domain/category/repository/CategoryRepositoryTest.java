package our.portfolio.devspace.domain.category.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.category.entity.CategoryType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("CategoryType에 해당하는 카테고리를 모두 찾아서 반환한다.")
    void findAllByType() {
        // ** Given **
        CategoryType categoryType = CategoryType.DEVELOP;

        // ** When **
        List<Category> categories = categoryRepository.findAllByType(categoryType);

        // ** Then **
        assertThat(categories.size()).isEqualTo(1);
    }
}