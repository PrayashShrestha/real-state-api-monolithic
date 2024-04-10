package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
import miu.ea.realestateapimonolithic.exception.InvalidInputException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.mapper.PropertyPhotoMapper;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import miu.ea.realestateapimonolithic.repository.PropertyPhotoRepository;
import miu.ea.realestateapimonolithic.repository.PropertyRepository;
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
    private final PropertyRepository propertyRepository;

    @Override
    public List<PropertyPhotoDto> findPropertyPhotoByProperty(Long propertyId) {
        return propertyPhotoRepository.findPropertyPhotoByProperty(propertyId).stream().map(PropertyPhotoMapper.MAPPER::mapToPropertyPhotoDto).collect(Collectors.toList());
    }

    @Override
    public void savePropertyPhoto(PropertyPhoto propertyPhoto) {
            propertyPhotoRepository.save(propertyPhoto);
        }

    @Override
    public void updatePropertyPhoto(Long propertyId, MultipartFile[] multipartFile) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyException("Property Not Found"));
        try {
            for (MultipartFile photo : multipartFile) {
                Map clodinaryResult = cloudinaryService.upload(photo);
                PropertyPhoto propertyPhoto = new PropertyPhoto();
                propertyPhoto.setName((String) clodinaryResult.get("original_filename"));
                propertyPhoto.setImageUrl((String) clodinaryResult.get("url"));
                propertyPhoto.setImageId((String) clodinaryResult.get("public_id"));
                propertyPhoto.setProperty(property);
                propertyPhotoRepository.save(propertyPhoto);
                property.getPhotos().add(propertyPhoto);
            }
        }
            catch (IOException e) {
            throw new InvalidInputException("Image not valid");
        }
    }

    @Override
    public PropertyPhotoDto getOnePropertyPhoto(Long id) {
        return null;
    }

    @Override
    public void deletePropertyPhoto(Long photoId) {
        PropertyPhoto propertyPhoto = propertyPhotoRepository.findById(photoId).orElseThrow(() -> new NotFoundException("Property Photo Not Found. Id: " +photoId));
        try {
            cloudinaryService.delete(propertyPhoto.getImageId());
        } catch (IOException e) {
            throw new RuntimeException("Could not delete photo");
        }
        propertyPhotoRepository.deleteById(photoId);
    }
}



