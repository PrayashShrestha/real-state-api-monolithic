package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.service.BuyerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void addFavouriteProperty(Long buyerId, PropertyDto propertyDto) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found : " + buyerId));
        buyer.getFavouriteProperties().add(PropertyMapper.MAPPER.mapToProperty(propertyDto));
        buyerRepository.save(buyer);
    }

    @Override
    public List<PropertyDto> viewFavouriteProperties(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found : " + buyerId));
        return buyer.getFavouriteProperties().stream()
                .map(PropertyMapper.MAPPER::mapToPropertyDto)
                .collect(Collectors.toList());
    }
}
