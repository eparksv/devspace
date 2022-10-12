package our.portfolio.devspace.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.common.mapper.CategoryMapper;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.CategoryType;
import our.portfolio.devspace.domain.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> listCategories(CategoryType type) {

        return categoryMapper.toCategoryResponses(categoryRepository.findAllByType(type));
    }
}
