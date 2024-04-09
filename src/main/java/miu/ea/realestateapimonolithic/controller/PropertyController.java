package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
import miu.ea.realestateapimonolithic.exception.PropertyException;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.service.PropertyPhotoService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.PROPERTY_URL_PREFIX)
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyPhotoService propertyPhotoService;

    @PostMapping
    public ResponseEntity<String> saveProperty(@RequestBody PropertyDto propertyDto){
        propertyService.save(propertyDto);
        return new ResponseEntity<>("Property was created successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllForGuest(){
        List<PropertyDto> properties = propertyService.findAllByListingStatus();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getAllPropertyByUser(@PathVariable Long id) {
        PropertyDto property = propertyService.findById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyById(@PathVariable Long id) {
        propertyService.deleteById(id);
        return new ResponseEntity<>("Property Deleted Successfully", HttpStatus.OK);
    }

    @PostMapping("/{propertyId}/upload")
    public ResponseEntity<String> upload(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable Long propertyId) {
        propertyPhotoService.savePropertyPhoto(multipartFile, propertyId);
        return new ResponseEntity<>("Image uploaded successfully!", HttpStatus.OK);
    }

    @GetMapping("/{propertyId}/upload")
    public ResponseEntity<List<PropertyPhotoDto>> getPropertyPhotosByProperty(@PathVariable Long propertyId){
        List<PropertyPhotoDto> photos =  propertyPhotoService.findPropertyPhotoByProperty(propertyId);
        return new ResponseEntity<>(photos,HttpStatus.OK);
    }

}
