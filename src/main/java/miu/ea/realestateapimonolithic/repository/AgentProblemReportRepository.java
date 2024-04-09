package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentProblemReportRepository extends JpaRepository<AgentProblemReport, Long> {
}
