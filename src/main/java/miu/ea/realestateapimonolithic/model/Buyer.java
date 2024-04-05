package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends User {
    private PropertyTypeEnum propertyType;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private double maxPrice;
    private String favoriteLocation;
}
