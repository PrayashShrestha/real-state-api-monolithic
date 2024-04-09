package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.AgentReviewPreviewDto;

import java.util.List;

public interface AgentReviewService {

    void saveAgentReview(AgentReviewDto agentReview);

    List<AgentReviewPreviewDto> getAgentReviewsByAgentId(long id);

    List<AgentReviewPreviewDto> getAllAgentReview();
}
