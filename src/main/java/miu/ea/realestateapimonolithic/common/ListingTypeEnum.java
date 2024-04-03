package miu.ea.realestateapimonolithic.common;

public enum ListingTypeEnum {
    FOR_SALE("For Sale"),
    FOR_RENT("For Rent"),
    SOLD("Sold");

    private String type;
    ListingTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
