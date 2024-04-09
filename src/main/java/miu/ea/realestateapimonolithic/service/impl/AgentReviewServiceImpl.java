package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.exception.UserException;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentReviewServiceImpl implements AgentReviewService {
    private final AgentReviewRepository agentReviewRepository;
    private final AgentRepository agentRepository;
    private final BuyerRepository buyerRepository;

    @Override
    @Transactional
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

        // update averageRating for Agent
        Double averageRating = agentReviewRepository.getAverageRating(agentId);
        agent.setAverageRating(averageRating);
        agentRepository.save(agent);
    }

    @Override
    public List<AgentReviewDto> getAgentReviewsByAgentId(long id) {
        return agentReviewRepository.findAgentReviewByAgent_Id(id)
                .stream()
                .map(AgentReviewMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<AgentReviewDto> getAllAgentReview() {
        return agentReviewRepository.findAll()
                .stream()
                .map(AgentReviewMapper::toDto)
                .collect(Collectors.toList());
    }
}
