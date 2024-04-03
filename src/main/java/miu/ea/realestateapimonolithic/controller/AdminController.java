package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ADMIN_URL_PREFIX)
@RequiredArgsConstructor
public class AdminController {
    private final PropertyService propertyService;

    @GetMapping("/property/approve/{propertyId}")
    public ApiResponse<?> approveListing(@PathVariable Long propertyId) {
        propertyService.approveProperty(propertyId);
        return ApiResponse.builder()
                .success(true)
                .build();
    }

    @GetMapping("/property/reject/{propertyId}")
    public ApiResponse<?> rejectListing(@PathVariable Long propertyId) {
        propertyService.rejectProperty(propertyId);
        return ApiResponse.builder()
                .success(true)
                .build();
    }
}

