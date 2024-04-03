package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.service.PropertyService;
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
