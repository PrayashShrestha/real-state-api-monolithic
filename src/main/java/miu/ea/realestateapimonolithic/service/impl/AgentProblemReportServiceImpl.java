package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.MessageStatusEnum;
import miu.ea.realestateapimonolithic.common.ProblemReportStatusEnum;
import miu.ea.realestateapimonolithic.dto.AgentProblemReportDto;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.AgentProblemReportMapper;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import miu.ea.realestateapimonolithic.model.Message;
import miu.ea.realestateapimonolithic.repository.AgentProblemReportRepository;
import miu.ea.realestateapimonolithic.service.AgentProblemReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentProblemReportServiceImpl implements AgentProblemReportService {
    private final AgentProblemReportRepository agentProblemReportRepository;

    @Override
    public void saveProblem(AgentProblemReport problemReport) {
        problemReport.setStatus(ProblemReportStatusEnum.NEW);
        agentProblemReportRepository.save(problemReport);
    }

    @Override
    public void updateStatus(AgentProblemReport problemReport) {
        AgentProblemReport existingReport = agentProblemReportRepository.findById(problemReport.getId()).orElseThrow(
                () -> {
                    return new NotFoundException("Problem report not found, id=" + problemReport.getId());
                }
        );
        existingReport.setStatus(problemReport.getStatus());
        agentProblemReportRepository.save(existingReport);
    }

    @Override
    public List<AgentProblemReportDto> getAllNewReports() {
        List<AgentProblemReport> list = agentProblemReportRepository.findByStatus(ProblemReportStatusEnum.NEW);
        return list.stream().map(report -> AgentProblemReportMapper.toDto(report)).collect(Collectors.toList());
    }

    @Override
    public List<AgentProblemReportDto> getAllActiveReports() {
        List<AgentProblemReport> list = agentProblemReportRepository.findByStatusIsNot(ProblemReportStatusEnum.CLOSED);
        return list.stream().map(report -> AgentProblemReportMapper.toDto(report)).collect(Collectors.toList());
    }
}
