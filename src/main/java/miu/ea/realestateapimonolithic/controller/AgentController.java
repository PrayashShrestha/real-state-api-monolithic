package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.Language;
import miu.ea.realestateapimonolithic.model.Qualification;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.AGENT_URL_PREFIX)
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @PostMapping("/{id}/add-languages")
    public ResponseEntity<String> addLanguages(@PathVariable Long id, @RequestParam List<String> languages){
        agentService.addLanguages(id, languages);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    @PostMapping("/{id}/add-qualifications")
    public ResponseEntity<String> addQualifications(@PathVariable Long id, @RequestParam List<String> qualifications){
        agentService.addQualification(id, qualifications);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }
}
