package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.congifuration.SecurityConfig;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.dto.LoginRequest;
import miu.ea.realestateapimonolithic.dto.TokenResponse;
import miu.ea.realestateapimonolithic.exception.EmailAlreadyExistsException;
import miu.ea.realestateapimonolithic.exception.MismatchException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.exception.InvalidInputException;
import miu.ea.realestateapimonolithic.mapper.UserMapper;
import miu.ea.realestateapimonolithic.model.*;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.repository.RoleRepository;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import miu.ea.realestateapimonolithic.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BuyerRepository buyerRepository;
    private final AgentRepository agentRepository;
    private final SecurityConfig securityConfig;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void saveUser(AccountRegistrationRequest accountRegistrationRequest) {
        // check duplicate email
        Optional<User> optionalUser = userRepository.findByEmail(accountRegistrationRequest.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // validate role
        if (accountRegistrationRequest.getUserRole() != RoleEnum.AGENT &&
                accountRegistrationRequest.getUserRole() != RoleEnum.SELLER &&
                accountRegistrationRequest.getUserRole() != RoleEnum.BUYER) {
            throw new InvalidInputException("Invalid user role.");
        }

        Role role = roleRepository.findByRole(accountRegistrationRequest.getUserRole());

        // map dto to entity
        User user = UserMapper.MAPPER.mapToUser(accountRegistrationRequest);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRole(role);
        user.setStatus(UserStatusEnum.ACTIVE);

        if (accountRegistrationRequest.getUserRole() == RoleEnum.BUYER) {
            Buyer buyer = new Buyer();
            BeanUtils.copyProperties(user, buyer);
            // set default value for preference
            BuyerPreference preference = new BuyerPreference();
            preference.setPropertyType(
              .HOUSE);
            buyer.setPreference(preference);
            buyerRepository.save(buyer);
        } else if (userDto.getUserRole() == RoleEnum.AGENT) {
            Agent agent = new Agent();
            BeanUtils.copyProperties(user, agent);
            agentRepository.save(agent);
        } else if (userDto.getUserRole() == RoleEnum.SELLER) {
            userRepository.save(user);
        }
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
        updatedUser.setTel(user.getTel());
        updatedUser.setLocation(user.getLocation());

        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found."));

        if (!securityConfig.passwordEncoder().matches(oldPassword, user.getPassword())) {
            throw new MismatchException("Your old password didn't match with existing password.");
        }

        String encodedNewPassword = securityConfig.passwordEncoder().encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    public void activateUser(long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> {
                    LOG.info("User {} not found", id);
                    return new NotFoundException("User not found, id=" + id);
                }
        );
        // update user status
        existingUser.setStatus(UserStatusEnum.ACTIVE);
        userRepository.save(existingUser);
    }

    public void deactivateUser(long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> {
                    LOG.info("User {} not found", id);
                    return new NotFoundException("User not found, id=" + id);
                }
        );
        // update user status
        existingUser.setStatus(UserStatusEnum.DEACTIVE);
        userRepository.save(existingUser);
    }

    public ApiResponse<?> login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());
        if (!optionalUser.isPresent()) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Incorrect email or password.")
                    .build();
        }
        User user = optionalUser.get();
        if (securityConfig.passwordEncoder().matches(req.getPassword(), user.getPassword())) {
            TokenResponse tokenResponse = createTokenResponse();
            return ApiResponse.builder()
                    .success(true)
                    .data(tokenResponse)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Incorrect email or password.")
                    .build();
        }
    }

    private TokenResponse createTokenResponse() {
        String token = UUID.randomUUID().toString(); // implement later
        return TokenResponse.builder().accessToken(token).expiresIn(Constant.TOKEN_EXPIRATION_DURATION).build();
    }

}
