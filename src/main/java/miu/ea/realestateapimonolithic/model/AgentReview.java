package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AgentReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comment;
    private int rating;

    @JsonBackReference(value = "agent-review")
    @ManyToOne
    private Agent agent;

    @ManyToOne
    private User reviewer;
}
