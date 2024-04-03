package miu.ea.realstateapimonolithic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import miu.ea.realstateapimonolithic.common.PropertyTypeEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerPreference {
    @Id
    private long id;

    private PropertyTypeEnum propertyType;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private double maxPrice;
    private String favoriteLocation;
}
