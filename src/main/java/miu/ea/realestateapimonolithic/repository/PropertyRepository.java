package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.common.ListingStatusEnum;
import miu.ea.realestateapimonolithic.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

    @Query("Select p from Property p join p.user u where u.id= :userId")
    List<Property> findAllByUser(Long userId);

    @Query("Select p from Property p join p.user u where u.id= :userId and p.listingType = :listingType")
    List<Property> findAllByUserAndListingStatus(Long userId, ListingStatusEnum listingType);

    List<Property> findAllByListingStatus(ListingStatusEnum listingType);
}
