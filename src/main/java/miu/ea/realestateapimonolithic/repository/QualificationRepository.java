package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Optional<Qualification> findByAgentIdAndQualification(Long userId, String qualification);
}
