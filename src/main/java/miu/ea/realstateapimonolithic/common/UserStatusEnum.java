package miu.ea.realstateapimonolithic.common;

public enum UserStatusEnum {
    ACTIVE("Active"),
    DEACTIVE("Deactive"),
    LOCKED("Locked");

    private String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
