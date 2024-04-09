package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import miu.ea.realestateapimonolithic.repository.AgentProblemReportRepository;
import miu.ea.realestateapimonolithic.service.AgentProblemReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentProblemReportServiceImpl implements AgentProblemReportService {
    private final AgentProblemReportRepository agentProblemReportRepository;

    public void saveProblem(AgentProblemReport problemReport) {
        AgentProblemReport problem = new AgentProblemReport();
        BeanUtils.copyProperties(problemReport, problem);

        agentProblemReportRepository.save(problem);
    }
}
