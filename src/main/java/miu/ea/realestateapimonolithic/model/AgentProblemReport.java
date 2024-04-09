package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.ProblemEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AgentProblemReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProblemEnum problem;

    private String details;

    @ManyToOne
    private Agent agent;
    @ManyToOne
    private User reporter;

}
