package miu.ea.realestateapimonolithic.common;

import lombok.Getter;

@Getter
public enum RoleEnum {
    BUYER("ROLE_BUYER"),
    SELLER("ROLE_SELLER"),
    AGENT("ROLE_AGENT"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return String.valueOf(authority);
    }

}
