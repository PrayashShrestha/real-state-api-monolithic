package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentDto;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.AgentSearchRequest;
import miu.ea.realestateapimonolithic.dto.SearchResponse;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.AgentMapper;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.model.*;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.CustomAgentRepository;
import miu.ea.realestateapimonolithic.repository.LanguageRepository;
import miu.ea.realestateapimonolithic.repository.QualificationRepository;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final CustomAgentRepository customAgentRepository;
    private final AgentRepository agentRepository;
    private final LanguageRepository languageRepository;
    private final QualificationRepository qualificationRepository;

    @Override
    public SearchResponse search(AgentSearchRequest searchRequest, Pageable pageable) {
        Page<Agent> page = customAgentRepository.searchAgent(searchRequest, pageable);
        List<Agent> list = page.getContent();

        return SearchResponse.builder()
                .success(true)
                .data(list.stream().map(AgentMapper::toDto).collect(Collectors.toList()))
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    @Override
    public AgentDto getAgentById(Long userId) {
        Agent existingAgent = agentRepository.findById(userId).orElseThrow(
                () -> {
                    return new NotFoundException("User not found, id=" + userId);
                }
        );
        AgentDto agentDto = new AgentDto();
        BeanUtils.copyProperties(existingAgent, agentDto);

        if (existingAgent.getReviews() != null) {
            List<AgentReviewDto> reviews = existingAgent.getReviews().stream()
                    .map(AgentReviewMapper::toDto)
                    .collect(Collectors.toList());
            agentDto.setReviews(reviews);
        }

        return agentDto;
    }



    @Override
    public void addLanguages(Long id, List<String> languages) {
        Agent agent = agentRepository.findById(id).orElseThrow(()-> new NotFoundException("Agent not found."));

        List<Language> languagesList = languages.stream()
                .map(language -> languageRepository.findByLanguage(language)
                        .orElseGet(() -> {
                            Language newLanguage = new Language();
                            newLanguage.setLanguage(language);
                            newLanguage.setAgent(agent);
                            return languageRepository.save(newLanguage);
                        }))
                .collect(Collectors.toList());

        agent.setLanguages(languagesList);
        agentRepository.save(agent);
    }

    @Override
    public void addQualification(Long id, List<String> qualifications) {
        Agent agent = agentRepository.findById(id).orElseThrow(()-> new NotFoundException("Agent not found."));
        List<Qualification> quaificationList = qualifications.stream()
                .map(qualification -> qualificationRepository.findByQualification(qualification)
                        .orElseGet(() -> {
                            Qualification newQualification = new Qualification();
                            newQualification.setQualification(qualification);
                            newQualification.setAgent(agent);
                            return qualificationRepository.save(newQualification);
                        }))
                .collect(Collectors.toList());

        agent.setQualifications(quaificationList);
        agentRepository.save(agent);
    }
}
