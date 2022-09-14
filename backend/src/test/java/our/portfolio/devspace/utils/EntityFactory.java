package our.portfolio.devspace.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.reflect.FieldUtils;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.utils.dummy.DummyJob;
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
            .job(jobEntity(profile.getJob()))
            .user(new DummyUser(1L).userEntity())
            .build();

        if (profile.getId() != null) {
            setIdField(entity, profile.getId());
        }

        return entity;
    }

    public static List<Job> jobEntities(JobType type) throws IllegalAccessException {
        List<Job> entities = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            DummyJob job = new DummyJob(i);
            job.setType(type);
            job.setTitle(type.toString() + i);
            entities.add(jobEntity(job));
        }

        return entities;
    }

    public static Job jobEntity(DummyJob job) throws IllegalAccessException {
        Job entity = new Job(job.getType(), job.getTitle());

        if (job.getId() != null) {
            setIdField(entity, job.getId());
        }

        return entity;
    }
}
