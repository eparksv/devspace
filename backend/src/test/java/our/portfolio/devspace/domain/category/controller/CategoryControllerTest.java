package our.portfolio.devspace.domain.category.controller;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.CategoryType;
import our.portfolio.devspace.domain.category.service.CategoryService;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.ControllerTestUtils;
import our.portfolio.devspace.utils.factory.CategoryFactory;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static our.portfolio.devspace.utils.ControllerTestUtils.WebSecurityTestConfiguration;

@AutoConfigureRestDocs
@WebMvcTest(CategoryController.class) //컨트롤러 테스트 설정
@Import(WebSecurityTestConfiguration.class) //API 권한 설정
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc; //API 요청 객체

    @MockBean
    CategoryService categoryService;

    @Test
    @DisplayName("카테고리를 출력한다.")
    void listCategories() throws Exception {
        // ** Given **
        List<CategoryResponse> responseDto = CategoryFactory.categoryResponses(CategoryType.DEVELOP);
        given(categoryService.listCategories(CategoryType.DEVELOP)).willReturn(responseDto);

        // ** When **
        ResultActions resultActions = mockMvc.perform(
                get("/api/categorys/{type}", "develop")
                        .contentType(MediaType.APPLICATION_JSON)
        );


        // ** Then **
        HttpResponseBody<List<CategoryResponse>> body = new HttpResponseBody<>("카테고리 목록이 조회되었습니다.", responseDto);
        resultActions.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(CommonTestUtils.valueToString(body))
        );

        // ** API Docs **
        resultActions.andDo(
                document("카테고리 목록을 성공적으로 조회한다.", resource(ResourceSnippetParameters.builder()
                        .summary("카테고리 목록")
                        .tag("Category")
                        .pathParameters(parameterWithName("type").type(SimpleType.STRING).description("카테고리 종류 ex) develop, design, marketing, planning, startup, etc"))
                        .responseSchema(schema("CategoryResponses"))
                        .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(responseFieldDescriptors()))
                        .build())));
    }

    private FieldDescriptors responseFieldDescriptors() {
        return new FieldDescriptors(
                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("카테고리 목록"),
                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                fieldWithPath("[].title").type(JsonFieldType.STRING).description("카테고리명"));
    }


}