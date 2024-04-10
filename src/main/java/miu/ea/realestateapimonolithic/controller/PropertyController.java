package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.PropertyPhotoDto;
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

    @GetMapping("/{userId}")
    public ResponseEntity<PropertyDto> getAllPropertyByUser(@PathVariable Long userId) {
        PropertyDto property = propertyService.findById(userId);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @GetMapping("/{userId}/currentListings")
    public ResponseEntity<List<PropertyDto>> getCurrentListings(@PathVariable Long userId){
        List<PropertyDto> propertyDto = propertyService.findCurrentListing(userId);
        return new ResponseEntity<>(propertyDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}/pastListings")
    public ResponseEntity<List<PropertyDto>> getPastListings(@PathVariable Long userId){
        List<PropertyDto> propertyDto = propertyService.findPastListing(userId);
        return new ResponseEntity<>(propertyDto, HttpStatus.OK);
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
