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
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> favouriteProperties;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<SearchCondition> searchConditions;
}
