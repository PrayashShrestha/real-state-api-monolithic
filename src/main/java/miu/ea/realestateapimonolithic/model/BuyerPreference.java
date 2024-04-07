package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.Embeddable;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Embeddable
public class BuyerPreference {
    private PropertyTypeEnum propertyType;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private double minPrice;
    private double maxPrice;
    private String favoriteLocation;
}
