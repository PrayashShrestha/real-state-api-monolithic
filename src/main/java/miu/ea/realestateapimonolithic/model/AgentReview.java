package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.AgentReviewStatusEnum;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AgentReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Integer rating;

    @JsonBackReference(value = "agent-review")
    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Buyer reviewer;

    private LocalDateTime reviewDate;

    @Enumerated(EnumType.STRING)
    private AgentReviewStatusEnum status;
}
