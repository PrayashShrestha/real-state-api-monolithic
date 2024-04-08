package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.service.BuyerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepository buyerRepository;

    @Override
    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Buyer not found.")
        );
    }

    @Override
    public void updateBuyerPreferences(Long id, BuyerPreference buyerPreference) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Buyer not found.")
        );

        buyer.setPreference(buyerPreference);
        buyerRepository.save(buyer);
    }
}
