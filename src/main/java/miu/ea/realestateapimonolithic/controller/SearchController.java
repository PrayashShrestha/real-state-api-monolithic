package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.SEARCH_URL_PREFIX)
@RequiredArgsConstructor
public class SearchController {
    private final PropertyService propertyService;

    @PostMapping("/property")
    public SearchResponse searchProperty(@RequestBody PropertySearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPageNumber()-1, searchRequest.getPageSize());

        return propertyService.search(searchRequest, pageRequest);
    }
}
