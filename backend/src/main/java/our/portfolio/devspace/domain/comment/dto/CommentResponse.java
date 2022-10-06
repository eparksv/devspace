package our.portfolio.devspace.domain.comment.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String name;
    private String profileImage;
    private String company;
    private String jobTitle;
    private String content;
    private LocalDateTime createdAt;

    public static CommentResponse of(Long id, String name, String profileImage, String company, String jobTitle,
        String content, LocalDateTime createdAt) {
        return new CommentResponse(id, name, profileImage, company, jobTitle, content, createdAt);
    }
}
