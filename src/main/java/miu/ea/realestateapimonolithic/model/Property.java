package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyPhoto> photos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ListingTypeEnum listingType;

    @Enumerated(EnumType.STRING)
    private ListingStatusEnum listingStatus;

    private LocalDateTime listingDate;
    private LocalDateTime expiredDate;

    private boolean deleted = Boolean.FALSE;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private User user;





    public void addPropertyPhoto(PropertyPhoto propertyPhoto){
        photos = new ArrayList<>();
        this.photos.add(propertyPhoto);
    }
}
