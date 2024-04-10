package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyPhotoService {

    List<PropertyPhotoDto> findPropertyPhotoByProperty(Long propertyId);

    void savePropertyPhoto(MultipartFile multipartFile, Long id);

    PropertyPhotoDto getOnePropertyPhoto(Long id);

    void deletePropertyPhoto(Long id);
}
