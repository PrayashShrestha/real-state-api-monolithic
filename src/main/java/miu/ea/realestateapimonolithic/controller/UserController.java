package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentReviewPreviewDto;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.USER_URL_PREFIX)
public class UserController {

    private final UserService userService;
    private final PropertyService propertyService;
    private final AgentReviewService agentReviewService;

    @PostMapping
    public void saveUser(@RequestBody UserDto user) {
        userService.saveUser(user);
    }

    @GetMapping("/{userId}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long userId){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(userId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<AgentReviewPreviewDto>> getAllAgentReviews(){
        List<AgentReviewPreviewDto> agentReviews = agentReviewService.getAllAgentReview();
        return new ResponseEntity<>(agentReviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{agentId}")
    public ResponseEntity<List<AgentReviewPreviewDto>> getAgentReviewById(@PathVariable Long agentId){
        List<AgentReviewPreviewDto> agentReviews = agentReviewService.getAgentReviewsByAgentId(agentId);
        return new ResponseEntity<>(agentReviews,HttpStatus.OK);
    }

}
