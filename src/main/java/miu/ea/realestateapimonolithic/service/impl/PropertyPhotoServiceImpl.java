package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
import miu.ea.realestateapimonolithic.exception.InvalidInputException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.mapper.PropertyPhotoMapper;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import miu.ea.realestateapimonolithic.repository.PropertyPhotoRepository;
import miu.ea.realestateapimonolithic.service.PropertyPhotoService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyPhotoServiceImpl implements PropertyPhotoService {

    private final PropertyPhotoRepository propertyPhotoRepository;
    private final CloudinaryServiceImpl cloudinaryService;
    private final PropertyService propertyService;

    @Override
    public List<PropertyPhotoDto> findPropertyPhotoByProperty(Long propertyId) {
        return propertyPhotoRepository.findPropertyPhotoByProperty(propertyId).stream().map(PropertyPhotoMapper.MAPPER::mapToPropertyPhotoDto).collect(Collectors.toList());
    }

    @Override
    public void savePropertyPhoto(MultipartFile multipartFile, Long propertyId) {
        try {
            Map clodinaryResult = cloudinaryService.upload(multipartFile);
            PropertyPhoto photo = new PropertyPhoto();
            photo.setName((String) clodinaryResult.get("original_filename"));
            photo.setImageUrl((String) clodinaryResult.get("url"));
            photo.setImageId((String) clodinaryResult.get("public_id"));
            Property property = PropertyMapper.MAPPER.mapToProperty(propertyService.findById(propertyId));
            photo.setProperty(property);
            propertyPhotoRepository.save(photo);
            propertyService.addPhotos(propertyId, photo);
        } catch (IOException e) {
            throw new InvalidInputException("Image not valid");
        }
    }

    @Override
    public PropertyPhotoDto getOnePropertyPhoto(Long id) {
        return PropertyPhotoMapper.MAPPER.mapToPropertyPhotoDto(propertyPhotoRepository.findById(id).orElseThrow(() -> new NotFoundException("Photo not found " + id)));
    }

    @Override
    public void deletePropertyPhoto(Long id) {
        propertyPhotoRepository.deleteById(id);
    }
}
