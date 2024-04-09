package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}/add-languages")
    public ResponseEntity<String> addLanguages(@PathVariable Long agentId, @RequestBody List<String> languages){
        agentService.addLanguages(agentId, languages);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    @PostMapping("/{id}/add-qualifications")
    public ResponseEntity<String> addQualifications(@PathVariable Long agentId, @RequestBody List<String> qualifications){
        agentService.addQualification(agentId, qualifications);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }
}
