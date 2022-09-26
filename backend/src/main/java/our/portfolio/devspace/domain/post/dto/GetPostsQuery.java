package our.portfolio.devspace.domain.post.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.service.pagination.PostPaginationService;
import our.portfolio.devspace.domain.post.service.pagination.RecentPostPaginationService;

@Setter
@Getter
public class GetPostsQuery {

    private Long cursor; // 현재 보고 있는 마지막 포스팅의 key: 모든 Filter에서 다음 페이지 스크롤 시 사용
    private String keyword; // 검색 단어: PostFilter.CONTENT,CATEGORY,HASHTAG에서 사용
    private Sort sort = Sort.by("id").descending(); // 정렬: 기본 정렬은 최신순, Filter.CONTENT,CATEGORY,HASHTAG에서 인기순 정렬로 변경 가능
    private PostFilter filter = PostFilter.NONE; // 필터: 기본 필터는 Filter.NONE

    public String getNextCursorQueryString(List<Post> posts) {
        return "cursor=" + posts.get(PostPaginationService.PAGE_SIZE - 1).getId();
    }

    public String getSortQueryString() {
        String sortString;

        if (this.sort.toString().contains("id")) {
            sortString = PostSort.RECENT.toString().toLowerCase();
        } else {
            sortString = PostSort.POPULAR.toString().toLowerCase();
        }

        return "sort=" + sortString;
    }

    public void setSort(PostSort sort) {
        if (sort.equals(PostSort.POPULAR)) {
            this.sort = Sort.by("ranking");
        }
    }

    public enum PostSort {
        RECENT,
        POPULAR
    }

    @AllArgsConstructor
    @Getter
    public enum PostFilter {
        NONE(RecentPostPaginationService.class.getSimpleName());
        /* TODO 각 기능 구현 시 주석 해제
        FOLLOW(), 팔로우한 사용자의 포스팅
        CONTENT(), 기본 검색
        CATEGORY(), 카테고리 검색
        HASHTAG(); 해시태그 검색
        */
        private final String serviceName;
    }
}
