package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyPhotoMapper {
    PropertyPhotoMapper MAPPER = Mappers.getMapper(PropertyPhotoMapper.class);

    PropertyPhotoDto mapToPropertyPhotoDto(PropertyPhoto propertyPhoto);
    PropertyPhoto mapToPropertyPhoto(PropertyPhotoDto propertyPhotoDto);
}
