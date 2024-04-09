package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import miu.ea.realestateapimonolithic.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.BUYER_URL_PREFIX)
public class BuyerController {
    private final BuyerService buyerService;
    private final AgentReviewService agentReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        return new ResponseEntity<>(buyerService.getBuyerById(id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addAgentReview(@RequestBody AgentReviewDto agentReviewDto){
        agentReviewService.saveAgentReview(agentReviewDto);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }
}
