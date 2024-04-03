package miu.ea.realstateapimonolithic.common;

public enum PropertyTypeEnum {
    HOUSE("House"),
    APARTMENT("Apartment"),
    CONDO("Condo"),
    LAND("Land");

    private String propertyType;
    PropertyTypeEnum(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyType() {
        return this.propertyType;
    }
}
