package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Embeddable
@Getter
@Setter
public class BuyerPreference {
    @Enumerated(EnumType.STRING)
    private PropertyTypeEnum propertyType;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;
    private Double minPrice;
    private Double maxPrice;
    private String favoriteLocation;
}
