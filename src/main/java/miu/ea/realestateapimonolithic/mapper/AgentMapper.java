package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.model.Agent;
import org.springframework.beans.BeanUtils;

public class AgentMapper {

    public static AgentDto toDto(Agent agent) {
        AgentDto agentDto = new AgentDto();
        BeanUtils.copyProperties(agent, agentDto);
        return agentDto;
    }
}
