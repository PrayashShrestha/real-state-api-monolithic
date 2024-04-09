package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.exception.AgentException;
import miu.ea.realestateapimonolithic.mapper.AgentReviewMapper;
import miu.ea.realestateapimonolithic.model.Agent;
import miu.ea.realestateapimonolithic.model.AgentReview;
import miu.ea.realestateapimonolithic.repository.AgentRepository;
import miu.ea.realestateapimonolithic.repository.AgentReviewRepository;
import miu.ea.realestateapimonolithic.service.AgentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

}
