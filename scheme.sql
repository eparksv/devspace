CREATE TABLE jobs
(
    id    INT AUTO_INCREMENT NOT NULL,
    title VARCHAR(10)        NOT NULL,
    type  VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_jobs PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime              NULL,
    modified_date datetime              NULL,
    subject       VARCHAR(255)          NOT NULL,
    provider      VARCHAR(255)          NOT NULL,
    `role`        VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NOT NULL,
    token   VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id)
);

ALTER TABLE refresh_tokens
    ADD CONSTRAINT uc_refresh_tokens_userid UNIQUE (user_id);

CREATE TABLE profiles
(
    user_id          BIGINT       NOT NULL,
    created_date     datetime     NULL,
    modified_date    datetime     NULL,
    name             VARCHAR(12)  NOT NULL,
    introduction     VARCHAR(255) NOT NULL,
    company          VARCHAR(30)  NULL,
    career           VARCHAR(30)  NULL,
    job_id           INT          NOT NULL,
    image            VARCHAR(255) NULL,
    background_image VARCHAR(255) NULL,
    CONSTRAINT pk_profiles PRIMARY KEY (user_id)
);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_JOB FOREIGN KEY (job_id) REFERENCES jobs (id);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE reference_links
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    profile_id BIGINT                NULL,
    title      VARCHAR(10)           NOT NULL,
    url        VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_reference_links PRIMARY KEY (id)
);

ALTER TABLE reference_links
    ADD CONSTRAINT FK_REFERENCE_LINKS_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profiles (user_id);

CREATE TABLE careers
(
    profile_id BIGINT      NOT NULL,
    company    VARCHAR(30) NOT NULL,
    duration   VARCHAR(30) NOT NULL,
    CONSTRAINT pk_careers PRIMARY KEY (profile_id)
);

ALTER TABLE careers
    ADD CONSTRAINT FK_CAREERS_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profiles (user_id);

CREATE TABLE hashtags
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_hashtags PRIMARY KEY (id)
);

CREATE TABLE category
(
    id    INT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime              NULL,
    modified_date datetime              NULL,
    title         VARCHAR(255)          NULL,
    content       VARCHAR(255)          NOT NULL,
    profile_id    BIGINT                NOT NULL,
    secret        BIT(1)                NOT NULL,
    category_id   INT                   NOT NULL,
    CONSTRAINT pk_posts PRIMARY KEY (id)
);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profiles (user_id);

CREATE TABLE hashtags
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255)          NOT NULL,
    post_id BIGINT                NOT NULL,
    CONSTRAINT pk_hashtags PRIMARY KEY (id)
);

ALTER TABLE hashtags
    ADD CONSTRAINT FK_HASHTAGS_ON_POST FOREIGN KEY (post_id) REFERENCES posts (id);