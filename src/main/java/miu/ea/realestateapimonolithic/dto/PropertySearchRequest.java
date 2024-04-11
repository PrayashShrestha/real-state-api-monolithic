package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Getter
@Setter
public class PropertySearchRequest {
    private PropertyTypeEnum propertyType;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;
    private Double minPrice;
    private Double maxPrice;
    private String location;
    private ListingTypeEnum listingType;
    private Integer pageNumber;
    private Integer pageSize;
}
