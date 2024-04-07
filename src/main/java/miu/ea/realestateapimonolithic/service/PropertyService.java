package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.model.Role;
import miu.ea.realestateapimonolithic.model.User;

import java.util.List;

public interface PropertyService {

    void save(Property property);

    List<PropertyDto> findAll();

    List<PropertyDto> findAllByUserAndListingStatus(Long userId);

    void deleteById(Long id);

    void delete(PropertyDto property);

    List<PropertyDto> findAllByListingStatus();

    PropertyDto findById(Long id) throws PropertyException;

    void approveProperty(Long propertyId);
    void rejectProperty(Long propertyId);
}
