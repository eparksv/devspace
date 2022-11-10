package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.domain.category.dto.CategoryResponse;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.like.dto.CreateLikeResponse;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.profile.entity.Profile;

import java.util.List;

@Mapper(uses = {EntityMapper.class})
public interface LikeMapper {


    CreateLikeResponse toCreateLikeResponse(Like like);

    @Mapping(source = "postId", target = "post", qualifiedBy = {EntityMapper.IdToEntity.class})
    @Mapping(source = "userId", target = "profile", qualifiedBy = {EntityMapper.IdToEntity.class})
    Like toEntity(Long postId, Long userId);
}
