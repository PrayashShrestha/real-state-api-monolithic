package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.model.Buyer;

public interface BuyerService {
    Buyer getBuyerById(Long id);
    void saveBuyer(Buyer buyer);
}
