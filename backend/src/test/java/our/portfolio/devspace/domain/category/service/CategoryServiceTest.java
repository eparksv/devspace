package our.portfolio.devspace.domain.category.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.CategoryMapper;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.category.entity.CategoryType;
import our.portfolio.devspace.domain.category.repository.CategoryRepository;
import our.portfolio.devspace.utils.factory.CategoryFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    CategoryService categoryService;

    @ParameterizedTest(name = "{0}에 해당하는 카테고리 목록을 반환한다.")
    @ValueSource(strings = {"develop", "design", "marketing", "planning", "startup", "etc"})
    @DisplayName("카테고리 목록을 반환한다.")
    void listCategories(String typeString) throws IllegalAccessException {
        // ** Given **
        CategoryType type = CategoryType.valueOf(typeString.toUpperCase());
        List<Category> categories = CategoryFactory.categoryEntities(type);

        given(categoryRepository.findAllByType(any(CategoryType.class))).will(invocation ->
                CategoryFactory.categoryEntities(invocation.getArgument(0)));

        given(categoryMapper.toCategoryResponses(anyList())).will(invocation -> {
            List<Category> categoriesArgument = invocation.getArgument(0);
            return CategoryFactory.categoryResponses(categoriesArgument.get(0).getType());
        });

        // ** When **
        List<CategoryResponse> responseDto = categoryService.listCategories(type);

        // ** Then **
        assertThat(responseDto).usingRecursiveComparison().isEqualTo(categories);
    }
}