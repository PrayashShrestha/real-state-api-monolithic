package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.repository.PropertyRepository;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;


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
}
