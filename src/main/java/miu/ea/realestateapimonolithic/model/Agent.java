package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends User {
    private String qualification;
    private String language;

    @OneToMany(mappedBy = "agent")
    private List<AgentReview> reviews = new ArrayList<>();
}
