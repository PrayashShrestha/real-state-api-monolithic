package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.ProblemReportStatusEnum;
import miu.ea.realestateapimonolithic.common.ProblemTypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgentProblemReportDto {
    private Long id;
    private ProblemTypeEnum problemType;
    private String details;
    private UserDto agent;
    private UserDto reporter;
    private LocalDateTime reportDate;
    private ProblemReportStatusEnum status;
}
