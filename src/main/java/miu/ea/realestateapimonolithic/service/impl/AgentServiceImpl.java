package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.exception.AgentException;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final AgentReviewRepository agentReviewRepository;
    @Override
    public void addAgentReview(AgentReviewDto agentReviewDto) {
        AgentReview agentReview = AgentReviewMapper.MAPPER.mapToAgentReview(agentReviewDto);
        agentReviewRepository.save(agentReview);
    }

    @Override
    public List<AgentReviewDto> getAllAgentReview(long id) {
        if(agentRepository.findById(id).isPresent()){
            Agent agent = agentRepository.findById(id).get();
            return  agent.getReviews()
                    .stream()
                    .map(AgentReviewMapper.MAPPER::mapToAgentReviewDto)
                    .collect(Collectors.toList());
        }
        else {
            try {
                throw new AgentException("Agent Not Found");
            } catch (AgentException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
