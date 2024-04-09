package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.BatchSize;

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

    private double averageRating;

    @JsonManagedReference(value = "agent-review")
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "agent")
    private List<AgentReview> reviews = new ArrayList<>();
}
