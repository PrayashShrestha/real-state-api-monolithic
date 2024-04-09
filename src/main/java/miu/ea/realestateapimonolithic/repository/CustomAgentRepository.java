package miu.ea.realestateapimonolithic.repository;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    static Specification<Agent> joinReviews() {
        return (root, query, criteriaBuilder) -> {
            root.fetch("reviews");
            return criteriaBuilder.equal(root.get("id"), root.get("reviews").get("id"));
        };
    }

    static Specification<Agent> withStatus(UserStatusEnum userStatus) {
        return (root, query, criteriaBuilder)-> criteriaBuilder.equal(root.get("status"), userStatus);
    }

    static Specification<Agent> nameLike(@Nullable String name) {
        return (root, query, criteriaBuilder)-> name == null ? null :
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    static Specification<Agent> qualificationLike(@Nullable String qualification) {
        return (root, query, criteriaBuilder)-> qualification == null ? null :
                criteriaBuilder.like(root.get("qualification"), "%" + qualification + "%");
    }

    static Specification<Agent> languageLike(@Nullable String language) {
        return (root, query, criteriaBuilder)-> language == null ? null :
                criteriaBuilder.like(root.get("language"), "%" + language + "%");
    }

    static Specification<Agent> locationLike(@Nullable String location) {
        return (root, query, criteriaBuilder)-> location == null ? null :
                criteriaBuilder.like(root.get("location"), "%" + location + "%");
    }

    static Specification<Agent> ratingGreaterThanEqual(@Nullable Integer rating) {
        return (root, query, criteriaBuilder)-> rating == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("averageRating"), rating);
    }
}
