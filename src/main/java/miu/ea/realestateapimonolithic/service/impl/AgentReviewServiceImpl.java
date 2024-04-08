package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentReviewServiceImpl implements AgentReviewService {
    private final AgentReviewRepository agentReviewRepository;

    @Override
    public void saveAgentReview(AgentReviewDto agentReview) {

    }

    @Override
    public List<AgentReviewDto> getAgentReviewsByAgentId(long id) {
        return null;
    }
}
