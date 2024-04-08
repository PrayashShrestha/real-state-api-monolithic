package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PropertyTypeEnum propertyType;
    private String location;
    private Double price;
    private String description;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;

    @OneToMany
    @JoinColumn(name = "property_id")
    private List<PropertyPhoto> photos;

    @Enumerated(EnumType.STRING)
    private ListingTypeEnum listingType;

    @Enumerated(EnumType.STRING)
    private ListingStatusEnum listingStatus;

//    @JsonBackReference(value = "user-property")
    @ManyToOne
//    @JoinColumn(name = "user-id")
    private User user;
}
