package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.SearchConditionDto;

import java.util.List;

public interface SearchConditionService {
    void saveSearchCondition(Long buyerId, SearchConditionDto searchConditionDto);

    void editSearchCondition(Long buyerId, Long searchConditionId, SearchConditionDto searchConditionDto);

    List<SearchConditionDto> getAllSearchCondition(Long buyerId);

    void deleteSearchCondition(Long buyerId,Long searchCondId);
}
