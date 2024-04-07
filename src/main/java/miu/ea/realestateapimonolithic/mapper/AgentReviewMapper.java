package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.AgentReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgentReviewMapper {

    AgentReviewMapper MAPPER = Mappers.getMapper(AgentReviewMapper.class);

    AgentReviewDto mapToAgentReviewDto(AgentReview agentReview);

    AgentReview mapToAgentReview(AgentReviewDto agentReviewDto);
}
