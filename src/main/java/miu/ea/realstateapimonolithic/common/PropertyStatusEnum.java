package miu.ea.realstateapimonolithic.common;

public enum PropertyStatusEnum {
    FOR_SALE("For Sale"),
    FOR_RENT("For Rent"),
    SOLD("Sold");

    private String status;
    PropertyStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
