package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.mapper.AgentMapper;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.repository.CustomAgentRepository;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final CustomAgentRepository customAgentRepository;

    @Override
    public SearchResponse search(AgentSearchRequest searchRequest, Pageable pageable) {
        Page<Agent> page = customAgentRepository.searchAgent(searchRequest, pageable);
        List<Agent> list = page.getContent();

        return SearchResponse.builder()
                .success(true)
                .data(list.stream().map(agent -> AgentMapper.toDto(agent)).collect(Collectors.toList()))
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
