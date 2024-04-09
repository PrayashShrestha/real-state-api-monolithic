package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.common.ProblemReportStatusEnum;
import miu.ea.realestateapimonolithic.model.AgentProblemReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentProblemReportRepository extends JpaRepository<AgentProblemReport, Long> {
    List<AgentProblemReport> findByStatus(ProblemReportStatusEnum status);
    List<AgentProblemReport> findByStatusIsNot(ProblemReportStatusEnum status);
}
