package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private long id;

    @Enumerated(EnumType.STRING)
    private PropertyTypeEnum propertyType;
    private String location;
    private double price;
    private String description;
    private int numOfBedrooms;
    private int numOfBathrooms;

    @OneToMany
    private List<PropertyPhoto> photos;

    @Enumerated(EnumType.STRING)
    private ListingTypeEnum listingType;

    @Enumerated(EnumType.STRING)
    private ListingStatusEnum listingStatus;

//    @JsonBackReference(value = "user-property")
    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private User user;
}
