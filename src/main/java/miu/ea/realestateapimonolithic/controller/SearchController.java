package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.service.AgentService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.SEARCH_URL_PREFIX)
@RequiredArgsConstructor
public class SearchController {
    private final PropertyService propertyService;
    private final AgentService agentService;

    @PostMapping("/property")
    public SearchResponse searchProperty(@RequestBody PropertySearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPageNumber()-1, searchRequest.getPageSize());

        return propertyService.search(searchRequest, pageRequest);
    }

    @PostMapping("/agent")
    public SearchResponse searchAgent(@RequestBody AgentSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPageNumber()-1, searchRequest.getPageSize());

        return agentService.search(searchRequest, pageRequest);
    }

    @GetMapping("/agent/{userId}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable Long userId) {
        return new ResponseEntity<>(agentService.getAgentById(userId), HttpStatus.OK);
    }
}
