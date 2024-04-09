package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.congifuration.SecurityConfig;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.LoginRequest;
import miu.ea.realestateapimonolithic.dto.TokenResponse;
import miu.ea.realestateapimonolithic.exception.EmailAlreadyExistsException;
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

import java.util.List;
import java.util.Optional;

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
             buyerRepository.save(buyer);
        } else if (accountRegistrationRequest.getUserRole() == RoleEnum.AGENT) {
            Agent agent = new Agent();
            BeanUtils.copyProperties(user, agent);
             agentRepository.save(agent);
        } else if (accountRegistrationRequest.getUserRole() == RoleEnum.SELLER) {
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

    public TokenResponse login(LoginRequest req) {
        return null;
    }

}
