package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;

public interface BuyerService {
    Buyer getBuyerById(Long id);
    void updateBuyerPreferences(Long id, BuyerPreference buyerPreference);
}
