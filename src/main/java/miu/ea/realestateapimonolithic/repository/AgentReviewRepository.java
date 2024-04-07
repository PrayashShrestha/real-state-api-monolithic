package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.AgentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentReviewRepository extends JpaRepository<AgentReview, Long> {
}
