package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import org.springframework.data.domain.Pageable;

public interface AgentService {
    SearchResponse search(AgentSearchRequest searchRequest, Pageable pageable);
}
