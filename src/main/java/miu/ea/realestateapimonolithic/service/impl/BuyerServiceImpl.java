package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.exception.AlreadyExistException;
import miu.ea.realestateapimonolithic.exception.InvalidInputException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.repository.BuyerRepository;
import miu.ea.realestateapimonolithic.repository.PropertyRepository;
import miu.ea.realestateapimonolithic.service.BuyerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepository buyerRepository;
    private final PropertyRepository propertyRepository;

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
        List<Property> favorites = buyer.getFavouriteProperties();
        if(favorites.contains(PropertyMapper.MAPPER.mapToProperty(propertyDto))){
            throw new AlreadyExistException("Property is already on favorites");
        }
        else{
        buyer.getFavouriteProperties().add(PropertyMapper.MAPPER.mapToProperty(propertyDto));
        buyerRepository.save(buyer);
        }
    }

    @Override
    public List<PropertyDto> viewFavouriteProperties(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found : " + buyerId));
        return buyer.getFavouriteProperties().stream()
                .map(PropertyMapper.MAPPER::mapToPropertyDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeFavouriteProperty(Long buyerId, PropertyDto propertyDto) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(() -> new NotFoundException("Buyer Not Found : " + buyerId));
        List<Property> favorites = buyer.getFavouriteProperties();
        if(propertyRepository.findById(propertyDto.getId()).isPresent()){
            Property userSelectedProperty =propertyRepository.findById(propertyDto.getId()).get();
            if(favorites.contains(userSelectedProperty)){
                favorites.remove(userSelectedProperty);
                buyerRepository.save(buyer);
             }
            else{
                throw new InvalidInputException("The property isn't a favorited property");
            }
        }else{
            throw new NotFoundException("Property Not Found");
        }
    }
}
