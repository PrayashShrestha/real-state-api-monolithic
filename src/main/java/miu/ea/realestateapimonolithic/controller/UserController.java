package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.*;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.USER_URL_PREFIX)
public class UserController {

    private final UserService userService;
    private final PropertyService propertyService;
    private final AgentReviewService agentReviewService;

    @GetMapping("/{userId}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long userId){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(userId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping
    public List<UserResponseDto> findAllUser(){
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestPart UserUpdateDto user, @RequestPart MultipartFile profilePhoto){
        userService.updateUser(id, user, profilePhoto);
        return new ResponseEntity<>("User profile updated successfully.", HttpStatus.OK);
    }

    @PutMapping("/{id}/update-password")
    public void updatePassword(@PathVariable long id, @RequestParam String oldPassword,@RequestParam String newPassword){
        userService.updatePassword(id, oldPassword, newPassword);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<AgentReviewDto>> getAllAgentReview(){
        List<AgentReviewDto> agentReviews = agentReviewService.getAllAgentReview();
        return new ResponseEntity<>(agentReviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{agentId}")
    public ResponseEntity<List<AgentReviewDto>> getAgentReviewById(@PathVariable Long agentId){
        List<AgentReviewDto> agentReviews = agentReviewService.getAgentReviewsByAgentId(agentId);
        return new ResponseEntity<>(agentReviews,HttpStatus.OK);
    }

    @GetMapping("/{userId}/deactivate")
    public ApiResponse<?> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ApiResponse.builder()
                .success(true)
                .message("User was deactivated.")
                .build();
    }

    @GetMapping("/{userId}/reset-password")
    public ApiResponse<?> resetUserPassword(@PathVariable Long userId) {
        String newPassword = userService.resetUserPassword(userId);
        String response = String.format("Password has been updated to %s",newPassword);
        return ApiResponse.builder()
                .success(true)
                .message(response)
                .build();
    }


}

