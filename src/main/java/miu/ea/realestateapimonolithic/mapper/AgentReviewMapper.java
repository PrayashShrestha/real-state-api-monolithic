package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.AgentReview;
import org.springframework.beans.BeanUtils;

public class AgentReviewMapper {

    public static AgentReviewDto toDto(AgentReview agentReview){
        AgentReviewDto reviewDto = new AgentReviewDto();
        BeanUtils.copyProperties(agentReview, reviewDto);

        UserDto agentDto = new UserDto();
        BeanUtils.copyProperties(agentReview.getAgent(), agentDto);
        reviewDto.setAgent(agentDto);

        UserDto reviewerDto = new UserDto();
        BeanUtils.copyProperties(agentReview.getReviewer(), reviewerDto);
        reviewDto.setReviewer(reviewerDto);
        return reviewDto;
    }

}
