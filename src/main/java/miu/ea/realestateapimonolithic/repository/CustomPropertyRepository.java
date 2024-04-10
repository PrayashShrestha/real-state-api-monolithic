package miu.ea.realestateapimonolithic.repository;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.common.PropertyTypeEnum;
import miu.ea.realestateapimonolithic.dto.PropertySearchRequest;
import miu.ea.realestateapimonolithic.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPropertyRepository {
    private final PropertyRepository propertyRepository;

    public Page<Property> searchProperty(PropertySearchRequest searchRequest, Pageable pageable) {
        Specification<Property> specs = Specification
                .where(withStatus(ListingStatusEnum.APPROVED))
                .and(propertyTypeEqual(searchRequest.getPropertyType()))
                .and(numOfBedroomsEqual(searchRequest.getNumOfBedrooms()))
                .and(numOfBathroomsEqual(searchRequest.getNumOfBathrooms()))
                .and(priceGreaterThanEqual(searchRequest.getMinPrice()))
                .and(priceLessThanEqual(searchRequest.getMaxPrice()))
                .and(locationLike(searchRequest.getLocation()));
        return propertyRepository.findAll(specs, pageable);
    }

    static Specification<Property> withStatus(ListingStatusEnum listingStatus) {
        return (root, query, criteriaBuilder)-> criteriaBuilder.equal(root.get("listingStatus"), listingStatus);
    }

    static Specification<Property> propertyTypeEqual(@Nullable PropertyTypeEnum propertyType) {
        return (root, query, criteriaBuilder)-> propertyType == null ? null :
                criteriaBuilder.equal(root.get("propertyType"), propertyType);
    }

    static Specification<Property> numOfBedroomsEqual(@Nullable Integer numOfBedrooms) {
        return (root, query, criteriaBuilder)-> numOfBedrooms == null ? null :
                criteriaBuilder.equal(root.get("numOfBedrooms"), numOfBedrooms);
    }

    static Specification<Property> numOfBathroomsEqual(@Nullable Integer numOfBathrooms) {
        return (root, query, criteriaBuilder)-> numOfBathrooms == null ? null :
                criteriaBuilder.equal(root.get("numOfBathrooms"), numOfBathrooms);
    }

    static Specification<Property> priceGreaterThanEqual(@Nullable Double minPrice) {
        return (root, query, criteriaBuilder)-> minPrice == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    static Specification<Property> priceLessThanEqual(@Nullable Double maxPrice) {
        return (root, query, criteriaBuilder)-> maxPrice == null ? null :
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    static Specification<Property> locationLike(@Nullable String location) {
        return (root, query, criteriaBuilder)-> location == null ? null :
                criteriaBuilder.like(root.get("location"), "%" + location + "%");
    }
}
