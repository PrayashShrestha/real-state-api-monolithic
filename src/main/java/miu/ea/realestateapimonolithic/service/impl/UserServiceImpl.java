package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.exception.EmailAlreadyExistsException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.UserMapper;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import miu.ea.realestateapimonolithic.service.UserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(UserDto userDto) {
        // validation here (check duplicate email)
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // map dto to entity
        User user = UserMapper.MAPPER.mapToUser(userDto);

        // validation here
        return userRepository.save(user);
    }

    @Override
    public User findUser(long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new NotFoundException("User not Found.");
        }
        return user.get();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if(retrievedUser.isEmpty()){
            throw new NotFoundException("User not Found.");
        }
        User updatedUser = retrievedUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setRole(user.getRole());
        updatedUser.setTel(user.getTel());
        updatedUser.setLocation(user.getLocation());
        updatedUser.setStatus(user.getStatus());

        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
