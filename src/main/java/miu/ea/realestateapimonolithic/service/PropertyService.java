package miu.ea.realestateapimonolithic.service;

public interface PropertyService {

    void approveProperty(Long propertyId);
    void rejectProperty(Long propertyId);
}
