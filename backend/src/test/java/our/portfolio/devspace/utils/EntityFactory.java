package our.portfolio.devspace.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.reflect.FieldUtils;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.utils.dummy.DummyCategory;
import our.portfolio.devspace.utils.dummy.DummyJob;
import our.portfolio.devspace.utils.dummy.DummyPost;
import our.portfolio.devspace.utils.dummy.DummyProfile;
import our.portfolio.devspace.utils.dummy.DummyUser;

public class EntityFactory {

    private static void setIdField(Object object, Number id) throws IllegalAccessException {
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
            .user(userEntity(new DummyUser(1L)))
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

    public static User userEntity(DummyUser user) throws IllegalAccessException {
        User entity = User.builder()
            .role(user.getRole())
            .subject(user.getSubject())
            .provider(user.getProvider())
            .build();

        if (user.getId() != null) {
            setIdField(entity, user.getId());
        }

        return entity;
    }

    public static List<Post> postEntities(int size) throws IllegalAccessException {
        List<Post> posts = new ArrayList<>();

        for (long i = 1; i <= size; i++) {
            DummyPost post = new DummyPost(i);
            post.setTitle(post.getTitle() + i);
            post.setContent(post.getContent() + i);
            post.setSecret(i % 2 != 0);
            post.setHashtags(List.of("태그" + i, "태그" + i + 1, "태그" + i + 2));
            post.setProfile(new DummyProfile(i));
            posts.add(postEntity(post));
        }

        return posts;
    }

    public static Post postEntity(DummyPost post) throws IllegalAccessException {
        List<Hashtag> hashtags = post.getHashtags().stream().map(Hashtag::new).collect(Collectors.toList());

        Post entity = Post.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .secret(post.isSecret())
            .category(categoryEntity(post.getCategory()))
            .profile(profileEntity(post.getProfile()))
            .hashtags(hashtags)
            .build();

        if (post.getId() != null) {
            setIdField(entity, post.getId());
        }

        return entity;
    }

    public static Category categoryEntity(DummyCategory category) throws IllegalAccessException {
        Category entity = new Category(category.getTitle());

        if (category.getId() != null) {
            setIdField(entity, category.getId());
        }

        return entity;
    }
}
