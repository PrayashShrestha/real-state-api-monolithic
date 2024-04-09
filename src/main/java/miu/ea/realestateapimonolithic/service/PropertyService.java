package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PropertyService {

    void save(PropertyDto propertyDto);

    List<PropertyDto> findAll();

    List<PropertyDto> findAllByUserAndListingStatus(Long userId);

    void deleteById(Long id);

    void delete(PropertyDto property);

    List<PropertyDto> findAllByListingStatus();

    PropertyDto findById(Long id) throws PropertyException;

    void approveProperty(Long propertyId);
    void rejectProperty(Long propertyId);

    SearchResponse search(PropertySearchRequest propertySearchRequest, Pageable pageable);
}
