package miu.ea.realstateapimonolithic.repository;

import miu.ea.realstateapimonolithic.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
