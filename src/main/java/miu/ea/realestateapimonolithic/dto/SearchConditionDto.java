package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchConditionDto {
    private Long id;
    private PropertyTypeEnum propertyType;
    private String nameOfSearch;
    private Double minPrice;
    private Double maxPrice;
    private ListingTypeEnum listingType;
    private String location;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;
}
