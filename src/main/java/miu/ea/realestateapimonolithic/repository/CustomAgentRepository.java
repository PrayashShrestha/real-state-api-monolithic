package miu.ea.realestateapimonolithic.repository;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.AgentReviewStatusEnum;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.model.Language;
import miu.ea.realestateapimonolithic.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAgentRepository {
    private final AgentRepository agentRepository;

    public Page<Agent> searchAgent(AgentSearchRequest searchRequest, Pageable pageable) {
        Specification<Agent> specs = Specification
                .where(withStatus(UserStatusEnum.ACTIVE))
                .and(joinReviews())
                .and(nameLike(searchRequest.getName()))
                .and(qualificationLike(searchRequest.getQualification()))
                .and(languageLike(searchRequest.getLanguage()))
                .and(ratingGreaterThanEqual(searchRequest.getRating()))
                .and(locationLike(searchRequest.getLocation()));
        return agentRepository.findAll(specs, pageable);
    }

    static Specification<Agent> joinQualifications(String qualification) {
        return (root, query, criteriaBuilder) -> {
            if (qualification == null) return null;
            Join<Agent, Qualification> agentQualifications = root.join("qualifications");
            return criteriaBuilder.equal(agentQualifications.get("qualification"), qualification);
        };
    }

    static Specification<Agent> joinLanguages(String language) {
        return (root, query, criteriaBuilder) -> {
            if (language == null) return null;
            Join<Agent, Language> agentLanguages = root.join("languages");
            return criteriaBuilder.equal(agentLanguages.get("language"), language);
        };
    }

    static Specification<Agent> joinReviews() {
        return (root, query, criteriaBuilder) -> {
            Join<Agent, AgentReview> agentReviews = root.join("reviews", JoinType.LEFT);
            agentReviews.on(criteriaBuilder.equal(agentReviews.get("status"), AgentReviewStatusEnum.ACTIVE));
            return criteriaBuilder.conjunction();
        };
    }

    static Specification<Agent> withStatus(UserStatusEnum userStatus) {
        return (root, query, criteriaBuilder)-> criteriaBuilder.equal(root.get("status"), userStatus);
    }

    static Specification<Agent> nameLike(@Nullable String name) {
        return (root, query, criteriaBuilder)-> name == null ? null :
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    /*static Specification<Agent> qualificationLike(@Nullable String qualification) {
        return (root, query, criteriaBuilder)-> qualification == null ? null :
                criteriaBuilder.like(root.get("qualification"), "%" + qualification + "%");
    }

    static Specification<Agent> languageLike(@Nullable String language) {
        return (root, query, criteriaBuilder)-> language == null ? null :
                criteriaBuilder.like(root.get("language"), "%" + language + "%");
    }*/

    static Specification<Agent> locationLike(@Nullable String location) {
        return (root, query, criteriaBuilder)-> location == null ? null :
                criteriaBuilder.like(root.get("location"), "%" + location + "%");
    }

    static Specification<Agent> ratingGreaterThanEqual(@Nullable Integer rating) {
        return (root, query, criteriaBuilder)-> rating == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("averageRating"), rating);
    }
}
