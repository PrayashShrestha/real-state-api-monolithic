package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.SearchConditionDto;
import miu.ea.realestateapimonolithic.model.SearchCondition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchConditionMapper {
    SearchConditionMapper MAPPER = Mappers.getMapper(SearchConditionMapper.class);

    SearchConditionDto mapToSearchConditionDto(SearchCondition SearchCondition);
    SearchCondition mapToSearchCondition(SearchConditionDto SearchConditionDto);
}
