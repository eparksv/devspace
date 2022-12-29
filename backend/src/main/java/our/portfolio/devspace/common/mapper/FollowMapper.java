package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.dto.CreateFollowResponse;
import our.portfolio.devspace.domain.follow.entity.Follow;

@Mapper(uses = {EntityMapper.class})
public interface FollowMapper {

    CreateFollowResponse toCreateFollowResponse(Follow follow);

    @Mapping(source = "followRequest.id", target = "followee", qualifiedBy = {EntityMapper.IdToEntity.class})
    @Mapping(source = "userId", target = "follower", qualifiedBy = {EntityMapper.IdToEntity.class})
    Follow toEntity(CreateFollowRequest followRequest, Long userId);
}
