package miu.ea.realstateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realstateapimonolithic.common.Constant;
import miu.ea.realstateapimonolithic.model.User;
import miu.ea.realstateapimonolithic.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.USER_URL_PREFIX)
public class UserController {

    private final UserService userService;

    @PostMapping
    public void saveUser(@RequestBody User user) {

    }
}
