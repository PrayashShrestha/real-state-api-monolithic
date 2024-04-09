package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentProblemReportDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import org.springframework.beans.BeanUtils;

public class AgentProblemReportMapper {

    public static AgentProblemReportDto toDto(AgentProblemReport report) {
        AgentProblemReportDto reportDto = new AgentProblemReportDto();
        BeanUtils.copyProperties(report, reportDto);

        UserDto agentDto = new UserDto();
        BeanUtils.copyProperties(report.getAgent(), agentDto);
        reportDto.setAgent(agentDto);

        UserDto reporterDto = new UserDto();
        BeanUtils.copyProperties(report.getReporter(), reporterDto);
        reportDto.setReporter(reporterDto);
        return reportDto;
    }
}
