package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
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

    @PostMapping
    public void saveUser(@RequestBody UserDto user) {
        userService.saveUser(user);
    }

    @GetMapping("/{id}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long id){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(id);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}
