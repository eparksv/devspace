package our.portfolio.devspace.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import our.portfolio.devspace.domain.profile.dto.SimpleProfileResponse;

@Getter
public class PostPreviewResponse {

    private final long id;
    private final SimpleProfileResponse profile;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;
    private final List<String> hashtags;
    private final String title;
    private final String content;
    private final int likeCount;
    private final int commentCount;

    @Builder
    public PostPreviewResponse(long id, SimpleProfileResponse profile, LocalDateTime createdDate, List<String> hashtags, String title, String content, int likeCount,
        int commentCount) {
        this.id = id;
        this.profile = profile;
        this.createdDate = createdDate;
        this.hashtags = hashtags;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
