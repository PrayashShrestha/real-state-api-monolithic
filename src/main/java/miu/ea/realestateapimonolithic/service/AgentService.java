package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import org.springframework.data.domain.Pageable;

public interface AgentService {
    SearchResponse search(AgentSearchRequest searchRequest, Pageable pageable);


    void addLanguages(Long id, List<String> languages);

    void addQualification(Long id, List<String> qualifications);

    AgentDto getAgentById(Long userId);
}
