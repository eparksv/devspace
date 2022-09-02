package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.common.mapper.EntityMapper.IdToEntity;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.post.dto.PostCreationResponseDto;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.service.HashtagService;

@Mapper(uses = {EntityMapper.class, HashtagService.class})
public interface PostMapper {

    PostCreationResponseDto toPostCreationResponseDto(Post post);

    @Mapping(source = "userId", target = "profile", qualifiedBy = {IdToEntity.class})
    @Mapping(source = "dto.hashtags", target = "boundHashtags")
    Post toEntity(Long userId, PostCreationRequestDto dto);
}
