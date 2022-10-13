package our.portfolio.devspace.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.CategoryType;
import our.portfolio.devspace.domain.category.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 타입으로 카테고리 목록을 요청하면 해당하는 카테고리 목록을 반환한다.
     *
     * @param type {@link CategoryType} 찾는 카테고리의 타입
     * @return 결과 메시지와 {@link CategoryResponse}의 List를 담은 {@link HttpResponseBody}, Status 200 OK
     */
    @GetMapping("/api/categorys/{type}")
    public ResponseEntity<HttpResponseBody<List<CategoryResponse>>> listCategories(@PathVariable CategoryType type) {
        HttpResponseBody<List<CategoryResponse>> body = new HttpResponseBody<>("카테고리 목록이 조회되었습니다.", categoryService.listCategories(type));
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
