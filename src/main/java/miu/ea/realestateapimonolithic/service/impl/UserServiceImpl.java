package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.exception.EmailAlreadyExistsException;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BuyerRepository buyerRepository;
    private final AgentRepository agentRepository;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void saveUser(UserDto userDto) {
        // check duplicate email
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // validate role
        if (userDto.getUserRole() != RoleEnum.AGENT &&
                userDto.getUserRole() != RoleEnum.SELLER &&
                userDto.getUserRole() != RoleEnum.BUYER) {
            throw new InvalidInputException("Invalid user role.");
        }

        Role role = roleRepository.findByRole(userDto.getUserRole());

        // map dto to entity
        User user = UserMapper.MAPPER.mapToUser(userDto);
        user.setRole(role);
        user.setStatus(UserStatusEnum.ACTIVE);

        if (userDto.getUserRole() == RoleEnum.BUYER) {
            Buyer buyer = new Buyer();
            BeanUtils.copyProperties(user, buyer);
            buyerRepository.save(buyer);
        } else if (userDto.getUserRole() == RoleEnum.AGENT) {
            Agent agent = new Agent();
            BeanUtils.copyProperties(user, agent);
            agentRepository.save(agent);
        } else if (userDto.getUserRole() == RoleEnum.SELLER) {
            userRepository.save(user);
        }
    }

}
