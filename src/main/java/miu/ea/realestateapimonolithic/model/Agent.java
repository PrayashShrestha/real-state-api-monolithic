package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @ElementCollection
    @Column(columnDefinition = "String[]")
    private List<String> qualifications;

    @ElementCollection
    @Column(columnDefinition = "String[]")
    private List<String> languages;

    private Double averageRating;

    @JsonManagedReference(value = "agent-review")
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<AgentReview> reviews = new ArrayList<>();
}
