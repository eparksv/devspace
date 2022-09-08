package our.portfolio.devspace.common.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.common.mapper.EntityMapper.IdToEntity;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.Post;

@Mapper(uses = EntityMapper.class)
public interface PostMapper {

    CreatePostResponse toCreatePostResponse(Post post);

    @Mapping(source = "userId", target = "profile", qualifiedBy = {IdToEntity.class})
    @Mapping(source = "dto.categoryId", target = "category", qualifiedBy = {IdToEntity.class})
    Post toEntity(Long userId, CreatePostRequest dto);

    default List<Hashtag> toHashtag(List<String> hashtagNames) {
        return hashtagNames.stream().map(Hashtag::new).collect(Collectors.toList());
    }
}
