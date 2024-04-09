package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.model.User;
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

    // move to AuthController
//    @PostMapping
//    public void saveUser(@RequestBody AccountRegistrationRequest user) {
//         userService.saveUser(user);
//    }

    @GetMapping("/{id}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long id){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(id);
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

    @GetMapping("/{userId}/deactivate")
    public ApiResponse<?> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ApiResponse.builder()
                .success(true)
                .build();
    }
}

