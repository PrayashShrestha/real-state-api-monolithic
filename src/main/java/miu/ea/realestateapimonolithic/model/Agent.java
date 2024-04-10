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
    @JsonManagedReference(value = "agent-language")
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Qualification> qualifications = new ArrayList<>();

    @JsonManagedReference(value = "agent-qualification")
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Language> languages = new ArrayList<>();

    private Double averageRating;

    @JsonManagedReference(value = "agent-review")
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<AgentReview> reviews = new ArrayList<>();
}
