package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.AgentReviewPreviewDto;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
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

    // move to AuthController
//    @PostMapping
//    public void saveUser(@RequestBody AccountRegistrationRequest user) {
//         userService.saveUser(user);
//    }

    @GetMapping("/{userId}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long userId){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(userId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable long id) {
        return userService.findUser(id);
    }

    @GetMapping
    public List<User> findAllUser(){
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<AgentReviewPreviewDto>> getAllAgentReview(){
        List<AgentReviewPreviewDto> agentReviews = agentReviewService.getAllAgentReview();
        return new ResponseEntity<>(agentReviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{agentId}")
    public ResponseEntity<List<AgentReviewPreviewDto>> getAgentReviewById(@PathVariable Long agentId){
        List<AgentReviewPreviewDto> agentReviews = agentReviewService.getAgentReviewsByAgentId(agentId);
        return new ResponseEntity<>(agentReviews,HttpStatus.OK);
    }


    @GetMapping("/{userId}/deactivate")
    public ApiResponse<?> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ApiResponse.builder()
                .success(true)
                .build();
    }
}

