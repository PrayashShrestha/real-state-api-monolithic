package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.AgentReviewPreviewDto;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentReviewServiceImpl implements AgentReviewService {
    private final AgentReviewRepository agentReviewRepository;

    @Override
    public void saveAgentReview(AgentReviewDto agentReviewDto) {
        AgentReview agentReview = AgentReviewMapper.MAPPER.mapToAgentReview(agentReviewDto);
        agentReviewRepository.save(agentReview);
    }

    @Override
    public List<AgentReviewPreviewDto> getAgentReviewsByAgentId(long id) {
        return agentReviewRepository.findAgentReviewByAgent_Id(id)
                .stream()
                .map(agentReview -> AgentReviewPreviewDto.builder()
                        .id(agentReview.getId())
                        .comment(agentReview.getComment())
                        .rating(agentReview.getRating())
                        .agentId(agentReview.getAgent().getId())
                        .agentEmail(agentReview.getAgent().getEmail())
                        .reviewerId(agentReview.getReviewer().getId())
                        .reviewerEmail(agentReview.getReviewer().getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<AgentReviewPreviewDto> getAllAgentReview() {
        return agentReviewRepository.findAll()
                .stream()
                .map(agentReview -> AgentReviewPreviewDto.builder()
                        .id(agentReview.getId())
                        .comment(agentReview.getComment())
                        .rating(agentReview.getRating())
                        .agentId(agentReview.getAgent().getId())
                        .agentEmail(agentReview.getAgent().getEmail())
                        .reviewerId(agentReview.getReviewer().getId())
                        .reviewerEmail(agentReview.getReviewer().getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
