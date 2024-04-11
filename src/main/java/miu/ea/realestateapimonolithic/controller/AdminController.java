package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ADMIN_URL_PREFIX)
@RequiredArgsConstructor
public class AdminController {
    private final PropertyService propertyService;
    private final UserService userService;

    @GetMapping("/property/{propertyId}/approve")
    public ApiResponse<?> approveListing(@PathVariable Long propertyId) {
        propertyService.approveProperty(propertyId);
        return ApiResponse.builder()
                .success(true)
                .message("Property has been approved.")
                .build();
    }

    @GetMapping("/property/{propertyId}/reject")
    public ApiResponse<?> rejectListing(@PathVariable Long propertyId) {
        propertyService.rejectProperty(propertyId);
        return ApiResponse.builder()
                .success(true)
                .message("Property has been rejected.")
                .build();
    }

    @GetMapping("/user/{userId}/activate")
    public ApiResponse<?> activateUser(@PathVariable Long userId) {
        userService.activateUser(userId);
        return ApiResponse.builder()
                .success(true)
                .message("User has been activated.")
                .build();
    }

    @GetMapping("/user/{userId}/profile/approve")
    public ApiResponse<?> approveUserProfile(@PathVariable Long userId) {
        userService.approveProfile(userId);
        return ApiResponse.builder()
                .success(true)
                .message("User profile has been approved.")
                .build();
    }

    @GetMapping("/user/{userId}/reset-password")
    public ApiResponse<?> resetUserPassword(@PathVariable Long userId) {
        String newPassword = userService.resetUserPassword(userId);
        String response = String.format("Password has been updated to %s", newPassword);
        return ApiResponse.builder()
                .success(true)
                .message(response)
                .build();
    }
}

