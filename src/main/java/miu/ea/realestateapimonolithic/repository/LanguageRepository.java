package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByAgentIdAndLanguage(Long userId, String language);
}
