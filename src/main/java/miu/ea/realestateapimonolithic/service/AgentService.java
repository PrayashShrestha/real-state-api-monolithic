package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.AgentReview;

import java.util.List;

public interface AgentService {

    void addAgentReview(AgentReviewDto agentReview);

    List<AgentReviewDto> getAllAgentReview(long id);
}
