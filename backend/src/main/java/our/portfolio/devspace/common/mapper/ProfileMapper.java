package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.common.mapper.EntityMapper.IdToEntity;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Mapper(uses = {EntityMapper.class})
public interface ProfileMapper {

    @Mapping(source = "userId", target = "user", qualifiedBy = {IdToEntity.class})
    @Mapping(source = "dto.jobId", target = "job", qualifiedBy = {IdToEntity.class})
    Profile toEntity(Long userId, CreateProfileRequest dto);

    CreateProfileResponse toCreateProfileResponse(Profile profile);
}
