package miu.ea.realstateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realstateapimonolithic.repository.PropertyRepository;
import miu.ea.realstateapimonolithic.service.PropertyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;


}
