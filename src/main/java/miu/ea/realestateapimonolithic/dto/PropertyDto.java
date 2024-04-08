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
    private Long id;

    private PropertyTypeEnum propertyType;
    private String location;
    private Double price;
    private String description;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;
    private ListingTypeEnum listingType;
    private ListingStatusEnum listingStatus;
}
