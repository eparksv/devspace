package our.portfolio.devspace.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.category.entity.CategoryType;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByType(CategoryType type);
}
