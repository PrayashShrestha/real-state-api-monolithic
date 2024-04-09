package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.ProblemReportStatusEnum;
import miu.ea.realestateapimonolithic.common.ProblemTypeEnum;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AgentProblemReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProblemTypeEnum problemType;

    @Column(length = 2000)
    private String details;

    @ManyToOne
    private User agent;
    @ManyToOne
    private User reporter;

    @Enumerated(EnumType.STRING)
    private ProblemReportStatusEnum status;

    private LocalDateTime reportDate;
}
