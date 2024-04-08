package miu.ea.realestateapimonolithic.common;

public enum UserStatusEnum {
    IN_REVIEW("In Review"),
    REJECTED("Rejected"), // admin rejects
    ACTIVE("Active"), // admin approves or activates locked account
    DEACTIVE("Deactive"), // when user wants to close account temporarily or admin deactivates the account
    LOCKED("Locked"); // when 5 login attempts fail

    private String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
