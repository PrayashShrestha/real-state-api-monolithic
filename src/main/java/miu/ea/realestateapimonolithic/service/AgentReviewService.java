package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;

import java.util.List;

public interface AgentReviewService {

    void saveAgentReview(AgentReviewDto agentReview);

    List<AgentReviewDto> getAgentReview(long id);
}
