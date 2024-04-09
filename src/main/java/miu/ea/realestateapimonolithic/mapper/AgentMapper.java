package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.model.Agent;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AgentMapper {

    public static AgentDto toDto(Agent agent) {
        AgentDto agentDto = new AgentDto();
        BeanUtils.copyProperties(agent, agentDto);

        if (agent.getReviews() != null) {
            List<AgentReviewDto> reviews = agent.getReviews().stream()
                    .map(AgentReviewMapper::toDto)
                    .collect(Collectors.toList());
            agentDto.setReviews(reviews);
        }

        return agentDto;
    }
}
