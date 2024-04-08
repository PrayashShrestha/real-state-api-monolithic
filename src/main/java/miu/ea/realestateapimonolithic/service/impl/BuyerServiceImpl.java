package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.model.Buyer;
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
    public void saveBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    @Override
    public void updateBuyer(Buyer buyer) {

    }
}
