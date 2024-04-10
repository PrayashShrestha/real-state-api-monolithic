package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchConditionRepository extends JpaRepository<SearchCondition, Long> {

    @Query("select s from SearchCondition s join s.buyer b where b.id = :buyerId")
    List<SearchCondition> getSearchConditionByBuyerId(Long buyerId);
}
