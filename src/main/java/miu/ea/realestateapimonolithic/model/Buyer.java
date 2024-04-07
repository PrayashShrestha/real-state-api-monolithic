package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends User {
    @Embedded
    private BuyerPreference preference;
}
