package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.SearchConditionDto;
import miu.ea.realestateapimonolithic.model.SearchCondition;
import org.springframework.beans.BeanUtils;

public class SearchConditionMapper {

    public static SearchCondition toEntity(SearchConditionDto searchConditionDto){
        SearchCondition searchCondition = new SearchCondition();
        BeanUtils.copyProperties(searchConditionDto, searchCondition);
        return searchCondition;
    }

    public static SearchConditionDto toDto(SearchCondition searchCondition){
        SearchConditionDto searchConditionDto = new SearchConditionDto();
        BeanUtils.copyProperties(searchCondition, searchConditionDto);
        return searchConditionDto;
    }
}
