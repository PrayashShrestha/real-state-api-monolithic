package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchConditionDto;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.exception.NotAuthorizedException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.SearchConditionMapper;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.SearchCondition;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.repository.CustomPropertyRepository;
import miu.ea.realestateapimonolithic.repository.SearchConditionRepository;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.service.SearchConditionService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchConditionServiceImpl implements SearchConditionService {
    private final SearchConditionRepository searchConditionRepository;
    private final BuyerRepository buyerRepository;
    private final PropertyService propertyService;

    @Override
    public void saveSearchCondition(Long buyerId, SearchConditionDto searchConditionDto) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found"));
        SearchCondition searchCondition = new SearchCondition();
        BeanUtils.copyProperties(searchConditionDto, searchCondition);
        searchCondition.setBuyer(buyer);
        searchConditionRepository.save(searchCondition);

    }

    @Override
    public void editSearchCondition(Long buyerId, Long searchConditionId, SearchConditionDto updatedSearchCondition) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("Search condition doesn't exist " + searchConditionId));
        if(searchCondition.getBuyer().getId() == buyerId){
            searchCondition.setNameOfSearch(updatedSearchCondition.getNameOfSearch());
            searchCondition.setLocation(updatedSearchCondition.getLocation());
            searchCondition.setMinPrice(updatedSearchCondition.getMinPrice());
            searchCondition.setMaxPrice(updatedSearchCondition.getMaxPrice());
            searchCondition.setListingType(updatedSearchCondition.getListingType());
            searchCondition.setPropertyType(updatedSearchCondition.getPropertyType());
            searchConditionRepository.save(searchCondition);
        }else {
            throw new NotAuthorizedException("Not Authorized to edit this condition " + buyerId);
        }

    }

    @Override
    public List<SearchConditionDto> getAllSearchCondition(Long buyerId) {
        return searchConditionRepository.getSearchConditionByBuyerId(buyerId)
                .stream()
                .map(SearchConditionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSearchCondition(Long buyerId, Long searchCondId) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchCondId).orElseThrow(() -> new NotFoundException("Search Condition Not Found"));
        if(searchCondition.getBuyer().getId() == buyerId){
            searchConditionRepository.deleteById(searchCondId);
        }else {
            throw new NotAuthorizedException("Not Authorized to delete this condition " + buyerId);
        }
    }

    @Override
    public SearchResponse searchBasedOnCondition(Long searchConditionId) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("Search Condition Not Found. Id:" + searchConditionId));
        PropertySearchRequest propertySearchRequest = new PropertySearchRequest();
        BeanUtils.copyProperties(searchCondition, propertySearchRequest);
//        propertySearchRequest.setPropertyType(searchCondition.getPropertyType());
//        propertySearchRequest.setMaxPrice(searchCondition.getMaxPrice());
//        propertySearchRequest.setLocation(searchCondition.getLocation());
//        propertySearchRequest.setListingType(searchCondition.getListingType());
//        propertySearchRequest.setMinPrice(searchCondition.getMinPrice());
//        propertySearchRequest.setNumOfBathrooms(searchCondition.getNumOfBathrooms());
//        propertySearchRequest.setNumOfBedrooms(searchCondition.getNumOfBedrooms());
        propertySearchRequest.setPageSize(5);
        propertySearchRequest.setPageNumber(1);
//        System.out.println(propertySearchRequest.toString());
        PageRequest pageRequest = PageRequest.of(propertySearchRequest.getPageNumber()-1, propertySearchRequest.getPageSize());
        return propertyService.search(propertySearchRequest, pageRequest);
    }
}
