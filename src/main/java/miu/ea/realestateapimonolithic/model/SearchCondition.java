package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.ListingTypeEnum;

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

    private String nameOfSearch;

    private Double minPrice;

    private Double maxPrice;

    private ListingTypeEnum listingTypeEnum;

    private String location;

    @PrePersist
    public void prePersist(){
        if(minPrice == null){
            minPrice = 0.0;
        }
        if(listingTypeEnum == null){
            listingTypeEnum = ListingTypeEnum.FOR_SALE;
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
