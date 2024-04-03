package miu.ea.realstateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realstateapimonolithic.common.Constant;
import miu.ea.realstateapimonolithic.model.Property;
import miu.ea.realstateapimonolithic.service.PropertyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.PROPERTY_URL_PREFIX)
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public void saveProperty(@RequestBody Property property) {

    }


}
