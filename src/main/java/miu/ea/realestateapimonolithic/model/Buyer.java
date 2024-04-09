package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends User {
    @Embedded
    private BuyerPreference preference;

    @OneToMany
    @JoinTable(
            name = "favorite_properties",
            joinColumns = @JoinColumn(name = "buyer-id"),
            inverseJoinColumns = @JoinColumn(name = "property-id")
    )
    private List<Property> favouriteProperties;
}
