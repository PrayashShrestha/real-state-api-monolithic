package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;

@Mapper
public interface PropertyMapper {

    PropertyMapper MAPPER = Mappers.getMapper(PropertyMapper.class);

    PropertyDto mapToPropertyDto(Property property);

    Property mapToProperty(PropertyDto propertyDto);


}
