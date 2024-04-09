package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.AgentReviewPreviewDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.exception.UserException;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.mapper.UserMapper;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentReviewServiceImpl implements AgentReviewService {
    private final AgentReviewRepository agentReviewRepository;
    private final AgentRepository agentRepository;
    private final BuyerRepository buyerRepository;
    private final UserRepository userRepository;

    @Override
    public void saveAgentReview(AgentReviewDto agentReviewDto) {
        Long agentId = agentReviewDto.getAgent().getId();
        Long buyerId = agentReviewDto.getReviewer().getId();
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new UserException("Agent not found" + agentId));
        Buyer reviewer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Not a valid User" + buyerId));
        AgentReview agentReview = new AgentReview();
        agentReview.setId(agentReviewDto.getId());
        agentReview.setComment(agentReviewDto.getComment());
        agentReview.setRating(agentReviewDto.getRating());
        agentReview.setAgent(agent);
        agentReview.setReviewer(reviewer);
        agentReviewRepository.save(agentReview);
    }

    @Override
    public List<AgentReviewDto> getAgentReviewsByAgentId(long id) {
        return agentReviewRepository.findAgentReviewByAgent_Id(id)
                .stream()
                .map(agentReview -> {
                            Long agentId = agentReview.getAgent().getId();
                            Long reviewerId = agentReview.getReviewer().getId();
                            User agent = userRepository.findById(agentId).orElseThrow(() -> new NotFoundException("Agent not Found" + agentId));
                            UserDto agentDto = UserDto.builder().email(agent.getEmail()).name(agent.getName()).id(agentId).build();
                            User reviewer = userRepository.findById(reviewerId).orElseThrow(() -> new NotFoundException("Reviewer not Found" + reviewerId));
                            UserDto reviewerDto = UserDto.builder().email(reviewer.getEmail()).name(reviewer.getName()).id(reviewer.getId()).build();
                            AgentReviewDto agentReviewDto = AgentReviewDto.builder()
                                    .id(agentReview.getId())
                                    .comment(agentReview.getComment())
                                    .rating(agentReview.getRating())
                                    .agent(agentDto).reviewer(reviewerDto).build();
                            return agentReviewDto;
                        }
                )
                .collect(Collectors.toList());
    }
    @Override
    public List<AgentReviewDto> getAllAgentReview() {
        return agentReviewRepository.findAll()
                .stream()
                .map(agentReview -> {
                    Long agentId = agentReview.getAgent().getId();
                    Long reviewerId = agentReview.getReviewer().getId();
                    User agent = userRepository.findById(agentId).orElseThrow(() -> new NotFoundException("Agent not Found" + agentId));
                    UserDto agentDto = UserDto.builder().email(agent.getEmail()).name(agent.getName()).id(agentId).build();
                    User reviewer = userRepository.findById(reviewerId).orElseThrow(() -> new NotFoundException("Reviewer not Found" + reviewerId));
                    UserDto reviewerDto = UserDto.builder().email(reviewer.getEmail()).name(reviewer.getName()).id(reviewer.getId()).build();
                    AgentReviewDto agentReviewDto = AgentReviewDto.builder()
                        .id(agentReview.getId())
                        .comment(agentReview.getComment())
                        .rating(agentReview.getRating())
                        .agent(agentDto).reviewer(reviewerDto).build();
                    return agentReviewDto;
                }
                )
                .collect(Collectors.toList());
    }
}
