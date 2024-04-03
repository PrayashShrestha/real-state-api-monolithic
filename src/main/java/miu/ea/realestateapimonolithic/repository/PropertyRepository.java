package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
