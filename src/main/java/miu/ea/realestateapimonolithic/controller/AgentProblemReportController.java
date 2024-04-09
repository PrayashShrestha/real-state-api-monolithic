package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentProblemReportDto;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import miu.ea.realestateapimonolithic.service.AgentProblemReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.AGENT_REPORT_URL_PREFIX)
public class AgentProblemReportController {
    private final AgentProblemReportService agentProblemReportService;

    @PostMapping
    public ResponseEntity<String> saveAgentReport(@RequestBody AgentProblemReport agentProblemReport) {
        agentProblemReportService.saveProblem(agentProblemReport);
        return new ResponseEntity<>("Problem created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/new")
    public ResponseEntity<List<AgentProblemReportDto>> getAllNewReports() {
        return new ResponseEntity<>(agentProblemReportService.getAllNewReports(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AgentProblemReportDto>> getAllActiveReports() {
        return new ResponseEntity<>(agentProblemReportService.getAllActiveReports(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateStatus(@RequestBody AgentProblemReport agentProblemReport) {
        agentProblemReportService.updateStatus(agentProblemReport);
        return new ResponseEntity<>("Problem updated successfully.", HttpStatus.OK);
    }

}
