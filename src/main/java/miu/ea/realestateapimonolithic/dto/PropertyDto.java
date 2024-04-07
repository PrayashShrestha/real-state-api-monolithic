package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto {
    private long id;

    private PropertyTypeEnum propertyType;
    private String location;
    private double price;
    private String description;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private ListingTypeEnum listingType;
    private ListingStatusEnum listingStatus;
}
