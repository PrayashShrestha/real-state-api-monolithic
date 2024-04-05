package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
