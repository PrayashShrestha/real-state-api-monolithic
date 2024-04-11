package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PropertyTypeEnum propertyType;
    private String nameOfSearch;
    private Double minPrice;
    private Double maxPrice;
    @Enumerated(EnumType.STRING)
    private ListingTypeEnum listingType;
    private String location;
    private Integer numOfBedrooms;
    private Integer numOfBathrooms;

    @PrePersist
    void prePersist(){
        if(minPrice == null){
            minPrice = 0.0;
        }
        if(nameOfSearch == null){
            if(location != null){
                nameOfSearch = location + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            else{
                nameOfSearch = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }
    }

    @ManyToOne
    private Buyer buyer;


}
