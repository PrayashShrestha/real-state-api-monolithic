package miu.ea.realestateapimonolithic.dto;

import miu.ea.realestateapimonolithic.common.ProblemEnum;

public class AgentProblemReportDto {
    private Long id;
    private ProblemEnum problem;
    private String details;
    private UserDto agent;
    private UserDto reporter;
}
