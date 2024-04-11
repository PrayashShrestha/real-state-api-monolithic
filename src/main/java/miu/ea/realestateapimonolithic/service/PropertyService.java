package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {

    void save(PropertyDto propertyDto, MultipartFile [] multipartFiles);

    List<PropertyDto> findAll();

    List<PropertyDto> findAllByUserAndListingStatus(Long userId);

    void deleteById(Long id);

    List<PropertyDto> findAllByListingStatus();

    PropertyDto findById(Long id);

    void updatePhotos(Long propertyId, PropertyPhoto propertyPhoto);

    SearchResponse getPropertyByUserPreference(Long userId);

    void approveProperty(Long propertyId);
    void rejectProperty(Long propertyId);

    SearchResponse search(PropertySearchRequest propertySearchRequest, Pageable pageable);

    List<PropertyDto> findCurrentListing(Long userId);

    List<PropertyDto> findPastListing(Long userId);



}
