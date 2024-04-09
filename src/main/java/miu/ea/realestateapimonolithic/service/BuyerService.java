package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;

import java.util.List;

public interface BuyerService {
    Buyer getBuyerById(Long id);
    void updateBuyerPreferences(Long id, BuyerPreference buyerPreference);

    void addFavouriteProperty(Long buyerId, PropertyDto propertyDto);

    List<PropertyDto> viewFavouriteProperties(Long buyerId);
}
