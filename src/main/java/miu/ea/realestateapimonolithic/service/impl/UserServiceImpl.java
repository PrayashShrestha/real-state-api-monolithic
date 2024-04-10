package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.congifuration.SecurityConfig;
import miu.ea.realestateapimonolithic.dto.*;
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
import miu.ea.realestateapimonolithic.service.ProfilePhotoService;
import miu.ea.realestateapimonolithic.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BuyerRepository buyerRepository;
    private final AgentRepository agentRepository;
    private final SecurityConfig securityConfig;
    private final UserMapper userMapper;
    private final ProfilePhotoService profilePhotoService;

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
        User user = UserMapper.mapToUser(accountRegistrationRequest);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRole(role);
        user.setCreateDate(LocalDateTime.now());
        user.setStatus(UserStatusEnum.ACTIVE);

        if (accountRegistrationRequest.getUserRole() == RoleEnum.BUYER) {
            Buyer buyer = new Buyer();
            BeanUtils.copyProperties(user, buyer);
            // set default value for preference
            BuyerPreference preference = new BuyerPreference();
            preference.setPropertyType(PropertyTypeEnum.HOUSE);
            buyer.setPreference(preference);
            buyerRepository.save(buyer);
        } else if (role.getRole() == RoleEnum.AGENT) {
            Agent agent = new Agent();
            BeanUtils.copyProperties(user, agent);
            agent.setLanguages(List.of());
            agent.setQualifications(List.of());
            agentRepository.save(agent);
        } else if (role.getRole() == RoleEnum.SELLER) {
            userRepository.save(user);
        }
    }

    @Override
    public void saveAdmin(AccountRegistrationRequest accountRegistrationRequest) {
        // check duplicate email
        Optional<User> optionalUser = userRepository.findByEmail(accountRegistrationRequest.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // validate role
        if (accountRegistrationRequest.getUserRole() != RoleEnum.ADMIN) {
            throw new InvalidInputException("Invalid user role.");
        }

        Role role = roleRepository.findByRole(accountRegistrationRequest.getUserRole());

        // map dto to entity
        User user = new User();
        BeanUtils.copyProperties(accountRegistrationRequest, user);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRole(role);
        user.setCreateDate(LocalDateTime.now());
        user.setStatus(UserStatusEnum.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAllUsersWithRoles();
        return users.stream().map(userMapper::childTypeMapToUser).collect(Collectors.toList());
    }

    @Override
    public void updateUser(long id, UserUpdateDto user, MultipartFile profilePhoto) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if(retrievedUser.isEmpty()){
            throw new NotFoundException("User not Found.");
        }

        User updatedUser = retrievedUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setTel(user.getTel());
        updatedUser.setLocation(user.getLocation());
        updatedUser.setUpdateDate(LocalDateTime.now());
        updatedUser.setProfileInReview(true);

        String userPhotoUrl = profilePhotoService.saveProfilePhoto(profilePhoto, updatedUser);
        updatedUser.setProfilePhoto(userPhotoUrl);
        return userRepository.save(updatedUser);
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

    @Transactional
    public ApiResponse<?> login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());
        if (!optionalUser.isPresent()) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Incorrect email or password.")
                    .build();
        }
        User user = optionalUser.get();
        if (user.getStatus() == UserStatusEnum.DEACTIVE) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Your account has been deactivated.")
                    .build();
        }
        if (user.getStatus() == UserStatusEnum.REJECTED) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Your account has been rejected.")
                    .build();
        }
        // login successfully
        if (securityConfig.passwordEncoder().matches(req.getPassword(), user.getPassword())) {
            if (user.getFailedAttempt() > 0) {
                resetFailedAttempt(user);
            }
            TokenResponse tokenResponse = createTokenResponse();
            return ApiResponse.builder()
                    .success(true)
                    .data(tokenResponse)
                    .build();
        } else {
            if (user.getStatus() == UserStatusEnum.LOCKED) {
                if (unlock(user)) {
                    return ApiResponse.builder()
                            .success(true)
                            .message("Your account has been unlocked. Please try to login again.")
                            .build();
                } else {
                    return ApiResponse.builder()
                            .success(false)
                            .message("Your account has been locked due to "
                                    + Constant.MAX_LOGIN_FAILED_ATTEMPTS
                                    + " failed attempts. Please try again.")
                            .build();
                }
            } else {
                if (user.getFailedAttempt() < Constant.MAX_LOGIN_FAILED_ATTEMPTS) {
                    increaseFailedAttempt(user);
                    return ApiResponse.builder()
                            .success(false)
                            .message("Incorrect email or password.")
                            .build();
                } else {
                    lock(user);
                    return ApiResponse.builder()
                            .success(false)
                            .message("Your account has been locked due to "
                                    + Constant.MAX_LOGIN_FAILED_ATTEMPTS
                                    + " failed attempts. Please try again.")
                            .build();
                }
            }
        }
    }

    private void increaseFailedAttempt(User user) {
        int newFailAttempt = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempt(newFailAttempt, user.getEmail());
    }

    private void lock(User user) {
        user.setStatus(UserStatusEnum.LOCKED);
        user.setLockTime(LocalDateTime.now());
        userRepository.save(user);
    }

    private boolean unlock(User user) {
        long lockTime = user.getLockTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        long currentTime = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        if (lockTime + Constant.LOCK_TIME_DURATION < currentTime) {
            user.setStatus(UserStatusEnum.ACTIVE);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void resetFailedAttempt(User user) {
        userRepository.updateFailedAttempt(0, user.getEmail());
    }

    private TokenResponse createTokenResponse() {
        String token = UUID.randomUUID().toString(); // implement later
        return TokenResponse.builder().accessToken(token).expiresIn(Constant.TOKEN_EXPIRATION_DURATION).build();
    }

    public void approveProfile(long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> {
                    LOG.info("User {} not found", id);
                    return new NotFoundException("User not found, id=" + id);
                }
        );
        // update profile review to True
        existingUser.setProfileInReview(false);
        userRepository.save(existingUser);
    }

}
