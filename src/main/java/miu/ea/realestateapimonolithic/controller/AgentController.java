package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.AGENT_URL_PREFIX)
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<AgentReviewDto>> getAllAgentReview(@PathVariable Long id){
        List<AgentReviewDto> agentReviews = agentService.getAllAgentReview(id);
        return new ResponseEntity<>(agentReviews, HttpStatus.OK);
    }

    @PostMapping("/{id}/addReview")
    public ResponseEntity<String> addAgentReview(@PathVariable Long id, @RequestBody AgentReviewDto agentReviewDto){
        agentService.addAgentReview(agentReviewDto);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }
}
