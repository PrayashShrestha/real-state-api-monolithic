package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.AgentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentReviewRepository extends JpaRepository<AgentReview, Long> {
    @Query("Select review from AgentReview review join review.agent a where a.id= :agentId")
    List<AgentReview> findAgentReviewByAgent_Id (Long agentId);
}
