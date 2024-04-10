package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchConditionDto {

    private Long id;

    private String nameOfSearch;
    private Double minPrice;
    private Double maxPrice;
    private ListingTypeEnum listingTypeEnum;
    private String location;
}
