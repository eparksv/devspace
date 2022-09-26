package our.portfolio.devspace.domain.category.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
