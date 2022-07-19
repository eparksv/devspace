package our.portfolio.devspace.configuration.security.oauth.userinfo;

public enum OAuth2Provider {
    GOOGLE("google"),
    GITHUB("github");

    private final String provider;

    OAuth2Provider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public boolean equalsWith(String provider) {
        return this.provider.equalsIgnoreCase(provider);
    }
}
