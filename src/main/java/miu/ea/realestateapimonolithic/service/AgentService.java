package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.model.Agent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {
    SearchResponse search(AgentSearchRequest searchRequest, Pageable pageable);

    void addAgentReview(AgentReviewDto agentReviewDto);
    List<AgentReviewDto> getAllAgentReview(long id);

    void addLanguages(Long id, List<String> languages);

    void addQualification(Long id, List<String> qualifications);

    AgentDto getAgentById(Long userId);
}
