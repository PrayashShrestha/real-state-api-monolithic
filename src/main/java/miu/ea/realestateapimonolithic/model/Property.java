package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PropertyTypeEnum propertyType;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private double price;
    private String location;

    @Enumerated(EnumType.STRING)
    private ListingTypeEnum listingType;

    @Enumerated(EnumType.STRING)
    private ListingStatusEnum listingStatus;
}
