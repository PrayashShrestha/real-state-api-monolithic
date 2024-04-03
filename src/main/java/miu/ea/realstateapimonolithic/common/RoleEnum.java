package miu.ea.realstateapimonolithic.common;

public enum RoleEnum {
    BUYER("ROLE_BUYER"),
    SELLER("ROLE_SELLER"),
    AGENT("ROLE_AGENT"),
    ADMIN("ROLE_ADMIN");

    private String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return String.valueOf(authority);
    }

    public String getAuthority() {
        return authority;
    }
}
