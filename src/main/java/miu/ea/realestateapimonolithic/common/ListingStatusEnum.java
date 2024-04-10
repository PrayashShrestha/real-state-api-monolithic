package miu.ea.realestateapimonolithic.common;

public enum ListingStatusEnum {
    IN_REVIEW("In Review"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    DELETE("Delete"),
    CLOSED("Closed");

    private String status;

    ListingStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
