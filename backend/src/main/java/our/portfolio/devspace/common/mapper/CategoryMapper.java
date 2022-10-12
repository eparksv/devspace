package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryResponse> toCategoryResponses(List<Category> categories);
}
