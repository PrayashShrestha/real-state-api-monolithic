package miu.ea.realstateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realstateapimonolithic.common.PropertyStatusEnum;
import miu.ea.realstateapimonolithic.common.PropertyTypeEnum;

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
    private PropertyStatusEnum propertyStatus;
}
