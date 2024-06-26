package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.exception.NotAuthorizedException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import miu.ea.realestateapimonolithic.exception.UserException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.model.*;
import miu.ea.realestateapimonolithic.repository.*;
import miu.ea.realestateapimonolithic.service.BuyerService;
import miu.ea.realestateapimonolithic.service.PropertyPhotoService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.utility.UtilityClass;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final CustomPropertyRepository customPropertyRepository;
    private final CloudinaryServiceImpl cloudinaryService;
    private final BuyerRepository buyerRepository;

    @Override
    public void save(PropertyDto propertyDto, MultipartFile [] multipartFiles) {
        Long userId = propertyDto.getUser().getId();
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            RoleEnum role = user.get().getRole().getRole();
            if(role == RoleEnum.AGENT || role == RoleEnum.SELLER){
//                Property property = PropertyMapper.MAPPER.mapToProperty(propertyDto);
                Property property = new Property();
                BeanUtils.copyProperties(propertyDto, property);
                property.setUser(user.get());
                for(MultipartFile photo: multipartFiles){
                    try {
                        Map clodinaryResult = cloudinaryService.upload(photo);
                        PropertyPhoto propertyPhoto = new PropertyPhoto();
                        propertyPhoto.setName((String) clodinaryResult.get("original_filename"));
                        propertyPhoto.setImageUrl((String) clodinaryResult.get("url"));
                        propertyPhoto.setImageId((String) clodinaryResult.get("public_id"));
                        propertyPhoto.setProperty(property);
                        property.addPropertyPhoto(propertyPhoto);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                property.setListingStatus(ListingStatusEnum.IN_REVIEW);
                property.setListingDate(LocalDateTime.now());
                propertyRepository.save(property);
            }else {
                throw new NotAuthorizedException("Not Authorized");
            }
        }
        else {
            throw new UserException("User Not Found");
        }
    }


    @Override
    public List<PropertyDto> findAll() {
        return propertyRepository.findAll()
                 .stream()
                .filter(property -> property.getListingStatus() != ListingStatusEnum.DELETE)
                 .map(PropertyMapper.MAPPER::mapToPropertyDto)
                 .collect(Collectors.toList());
    }

    @Override
    public SearchResponse findAllByUserAndListingStatus(Long userId) throws UserException {
        RoleEnum role;
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            role = user.getRole().getRole();
            List<Property> properties = propertyRepository.findAllByUser(userId);
            if (role == RoleEnum.ADMIN){
                List<Property> propertiesInReview =  propertyRepository.findAllByListingStatus(ListingStatusEnum.IN_REVIEW);
                List<PropertyDto> propertyDtosInreview= propertiesInReview.stream().map(PropertyMapper.MAPPER::mapToPropertyDto)
                        .collect(Collectors.toList());
                return UtilityClass.toSearchResponse(propertyDtosInreview);

            } else if (role == RoleEnum.BUYER) {
                return this.getPropertyByUserPreference(userId);
            }else{
                List<PropertyDto> guestUserProperties= properties.stream()
                        .filter(property -> property.getListingStatus() != ListingStatusEnum.DELETE)
                        .map(PropertyMapper.MAPPER::mapToPropertyDto)
                        .collect(Collectors.toList());
                return UtilityClass.toSearchResponse(guestUserProperties);
            }

        }
        else{
            throw new UserException("User Not Found");
        }
    }


    @Override
    public void deleteById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyException("Property Not Found"));
        property.setListingStatus(ListingStatusEnum.DELETE);
        propertyRepository.save(property);
    }

    @Override
    public List<PropertyDto> findAllByListingStatus() {
        return propertyRepository.findAllByListingStatus(ListingStatusEnum.APPROVED)
                .stream()
                .filter(property -> property.getListingStatus() != ListingStatusEnum.DELETE)
                .map(PropertyMapper.MAPPER::mapToPropertyDto)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDto findById(Long id) {
        if(propertyRepository.findById(id).isPresent()){
            Property p = propertyRepository.findById(id).get();
            if (p.getListingStatus() != ListingStatusEnum.DELETE){
                return PropertyMapper.MAPPER.mapToPropertyDto(p);
            }else{
                throw new PropertyException("Property Doesn't Exist");
            }
        } else {
                throw new PropertyException("Property Doesn't Exist");

        }
    }

    @Override
    public SearchResponse getPropertyByUserPreference(Long userId) {
        Buyer buyer = buyerRepository.findById(userId).orElseThrow(() -> new UserException("Buyer Not Found. Id" + userId));
        BuyerPreference preferences = buyer.getPreference();
        if(preferences!=null) {
            PropertySearchRequest propertySearchRequest = new PropertySearchRequest();
            BeanUtils.copyProperties(preferences, propertySearchRequest);
            propertySearchRequest.setPageSize(10);
            propertySearchRequest.setPageNumber(1);
            PageRequest pageRequest = PageRequest.of(propertySearchRequest.getPageNumber() - 1, propertySearchRequest.getPageSize());
            return this.search(propertySearchRequest, pageRequest);
        }else {
            return UtilityClass.toSearchResponse(this.findAllByListingStatus());
        }
    }

    @Override
    public void updatePhotos(Long propertyId, PropertyPhoto propertyPhoto) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException("Property Not found " + propertyId));
        if (property.getListingStatus() != ListingStatusEnum.DELETE){
            property.getPhotos().add(propertyPhoto);
            propertyRepository.save(property);
        }else {
            throw new PropertyException("Property Doesn't exist");
        }
    }

    @Override
    public void approveProperty(Long propertyId) {
        LOG.info("Started approving property {}", propertyId);

        Property existingProperty = propertyRepository.findById(propertyId).orElseThrow(
                () -> {
                    LOG.info("Property {} not found", propertyId);
                    return new NotFoundException("Property not found, id=" + propertyId);
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
                    return new NotFoundException("Property not found, id=" + propertyId);
                }
        );
        // update listing status
        existingProperty.setListingStatus(ListingStatusEnum.REJECTED);
        propertyRepository.save(existingProperty);

        LOG.info("Property {} has been rejected.", propertyId);
    }

    @Override
    public SearchResponse search(PropertySearchRequest searchRequest, Pageable pageable) {
        Page<Property> page = customPropertyRepository.searchProperty(searchRequest, pageable);
        List<Property> list = page.getContent();
        System.out.println("page content"+ list);

        return SearchResponse.builder()
                .success(true)
                .data(list.stream().map(this::toDto).collect(Collectors.toList()))
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    @Override
    public List<PropertyDto> findCurrentListing(Long id) {
        List<Property> properties = propertyRepository.findAllByUser(id);
        return properties.stream()
                .filter(property -> property.getListingStatus() == ListingStatusEnum.APPROVED
                        || property.getListingStatus() == ListingStatusEnum.IN_REVIEW)
                .map(PropertyMapper.MAPPER::mapToPropertyDto).collect(Collectors.toList());
    }

    @Override
    public List<PropertyDto> findPastListing(Long id) {
        List<Property> properties = propertyRepository.findAllByUser(id);
        return properties.stream()
                .filter(property -> property.getListingStatus() == ListingStatusEnum.CLOSED)
                .map(PropertyMapper.MAPPER::mapToPropertyDto).collect(Collectors.toList());
    }

    private PropertyDto toDto(Property property) {
        PropertyDto propertyDto = new PropertyDto();
        BeanUtils.copyProperties(property, propertyDto);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(property.getUser(), userDto);
        propertyDto.setUser(userDto);

        return propertyDto;
    }


}
