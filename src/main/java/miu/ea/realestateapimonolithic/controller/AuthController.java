package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.dto.LoginRequest;
import miu.ea.realestateapimonolithic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(Constant.LOGIN_URL)
    public ApiResponse<?> login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }

    @PostMapping(Constant.SIGNUP_URL)
    public ResponseEntity<String> register(@RequestBody AccountRegistrationRequest reg) {
        userService.saveUser(reg);
        return new ResponseEntity<>("User created successfully.", HttpStatus.OK);
    }

    @PostMapping(Constant.SIGNUP_ADMIN_URL)
    public ResponseEntity<String> registerAdmin(@RequestBody AccountRegistrationRequest reg) {
        userService.saveAdmin(reg);
        return new ResponseEntity<>("Admin created successfully.", HttpStatus.OK);
    }
}
