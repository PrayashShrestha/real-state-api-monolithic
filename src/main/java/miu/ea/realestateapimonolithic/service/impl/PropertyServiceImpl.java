package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import miu.ea.realestateapimonolithic.exception.UserException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.CustomPropertyRepository;
import miu.ea.realestateapimonolithic.repository.PropertyRepository;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final CustomPropertyRepository customPropertyRepository;

    @Override
    public void save(PropertyDto propertyDto) {
        Property property = PropertyMapper.MAPPER.mapToProperty(propertyDto);
        propertyRepository.save(property);
    }

    @Override
    public List<PropertyDto> findAll() {
        return propertyRepository.findAll()
                 .stream()
                 .map(PropertyMapper.MAPPER::mapToPropertyDto)
                 .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDto> findAllByUserAndListingStatus(Long userId) throws UserException {
        RoleEnum role;
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            role = user.getRole().getRole();
            List<Property> properties = propertyRepository.findAllByUser(userId);
            if (role == RoleEnum.ADMIN){
                return this.findAll();
            } else if (role == RoleEnum.BUYER) {
                return this.findAllByListingStatus();
            }else{
                return properties.stream()
                        .map(PropertyMapper.MAPPER::mapToPropertyDto)
                        .collect(Collectors.toList());
            }

        }
        else{
            throw new UserException("User Not Found");
        }
    }


    @Override
    public void deleteById(Long id) {
        if(propertyRepository.findById(id).isPresent()){
            propertyRepository.deleteById(id);
        }else {
            try {
                throw new PropertyException("Property Not Found");
            } catch (PropertyException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void delete(PropertyDto propertyDto) {
        Property property = PropertyMapper.MAPPER.mapToProperty(propertyDto);
        propertyRepository.delete(property);

    }

    @Override
    public List<PropertyDto> findAllByListingStatus() {
        return propertyRepository.findAllByListingStatus(ListingStatusEnum.APPROVED)
                .stream()
                .map((PropertyMapper.MAPPER::mapToPropertyDto))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDto findById(Long id) {
        if(propertyRepository.findById(id).isPresent()){
            Property p = propertyRepository.findById(id).get();
            return PropertyMapper.MAPPER.mapToPropertyDto(p);
        } else {
            try {
                throw new PropertyException("Property Doesn't Exist");
            } catch (PropertyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void approveProperty(Long propertyId) {
        LOG.info("Started approving property {}", propertyId);

        Property existingProperty = propertyRepository.findById(propertyId).orElseThrow(
                () -> {
                    LOG.info("Property {} not found", propertyId);
                    return new RuntimeException("Property not found, id=" + propertyId);
                }
        );
        // update listing status
        existingProperty.setListingStatus(ListingStatusEnum.APPROVED);
        propertyRepository.save(existingProperty);

        LOG.info("Property {} has been approved.", propertyId);
    }

    @Override
    public void rejectProperty(Long propertyId) {
        LOG.info("Started rejecting property {}", propertyId);

        Property existingProperty = propertyRepository.findById(propertyId).orElseThrow(
                () -> {
                    LOG.info("Property {} not found", propertyId);
                    return new RuntimeException("Property not found, id=" + propertyId);
                }
        );
        // update listing status
        existingProperty.setListingStatus(ListingStatusEnum.REJECTED);
        propertyRepository.save(existingProperty);

        LOG.info("Property {} has been rejected.", propertyId);
    }

    @Override
    public Page<Property> search(PropertySearchRequest searchRequest, Pageable pageable) {
        return customPropertyRepository.searchProperty(searchRequest, pageable);
    }
}
