package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentProblemReportDto;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;

import java.util.List;

public interface AgentProblemReportService {
    void saveProblem(AgentProblemReport problemReport);
    void updateStatus(AgentProblemReport problemReport);
    List<AgentProblemReportDto> getAllNewReports();
    List<AgentProblemReportDto> getAllActiveReports();
}
