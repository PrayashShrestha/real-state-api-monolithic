package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.SearchConditionDto;
import miu.ea.realestateapimonolithic.exception.NotAuthorizedException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.SearchConditionMapper;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.SearchCondition;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.repository.SearchConditionRepository;
import miu.ea.realestateapimonolithic.service.SearchConditionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchConditionServiceImpl implements SearchConditionService {
    private final SearchConditionRepository searchConditionRepository;
    private final BuyerRepository buyerRepository;

    @Override
    public void saveSearchCondition(Long buyerId, SearchConditionDto searchConditionDto) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found"));
//        SearchCondition searchCondition = SearchConditionMapper.MAPPER.mapToSearchCondition(searchConditionDto);
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
}
