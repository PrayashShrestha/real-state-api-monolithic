package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import miu.ea.realestateapimonolithic.service.UserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void saveUser(User user) {
        // validation here (check duplicate email)

        // validation here
        userRepository.save(user);
    }
}
