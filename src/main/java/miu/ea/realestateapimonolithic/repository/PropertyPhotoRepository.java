package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {

    @Query("Select p from PropertyPhoto p join p.property pr where pr.id= :propertyId")
    List<PropertyPhoto> findPropertyPhotoByProperty(Long propertyId);

}
