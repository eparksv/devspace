package our.portfolio.devspace.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.reflect.FieldUtils;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.utils.dummy.DummyProfile;
import our.portfolio.devspace.utils.dummy.DummyUser;

public class EntityFactory {

    public static void setIdField(Object object, Number id) throws IllegalAccessException {
        FieldUtils.writeField(object, "id", id, true);
    }

    public static Profile profileEntity(DummyProfile profile) throws IllegalAccessException {
        List<ReferenceLink> referenceLinks = profile.getReferenceLinks()
            .stream()
            .map(referenceLinkDto -> new ReferenceLink(referenceLinkDto.getTitle(), referenceLinkDto.getUrl()))
            .collect(Collectors.toList());

        Profile entity = Profile.builder()
            .company(profile.getCompany())
            .introduction(profile.getIntroduction())
            .name(profile.getName())
            .career(profile.getCareer())
            .referenceLinks(referenceLinks)
            .job(profile.getJob().jobEntity())
            .user(new DummyUser(1L).userEntity())
            .build();

        if (profile.getId() != null) {
            setIdField(entity, profile.getId());
        }

        return entity;
    }
}
