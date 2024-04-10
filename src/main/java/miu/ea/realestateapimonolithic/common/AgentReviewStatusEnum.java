package miu.ea.realestateapimonolithic.common;

public enum AgentReviewStatusEnum {
    ACTIVE("Active"),
    DELETED("Deleted");

    private String status;

    AgentReviewStatusEnum(String status) {
        this.status = status;
    }
}
