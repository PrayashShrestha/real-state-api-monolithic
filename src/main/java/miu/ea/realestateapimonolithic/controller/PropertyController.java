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
    public ResponseEntity<String> saveProperty(@RequestPart PropertyDto propertyDto, @RequestPart(name = "photo") MultipartFile[] photos){
        propertyService.save(propertyDto, photos);
        return new ResponseEntity<>("Property was created successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllForGuest(){
        List<PropertyDto> properties = propertyService.findAllByListingStatus();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PropertyDto> getAllPropertyByUser(@PathVariable Long userId) {
        PropertyDto property = propertyService.findById(userId);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/currentListings")
    public ResponseEntity<List<PropertyDto>> getCurrentListings(@PathVariable Long userId){
        List<PropertyDto> propertyDto = propertyService.findCurrentListing(userId);
        return new ResponseEntity<>(propertyDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/pastListings")
    public ResponseEntity<List<PropertyDto>> getPastListings(@PathVariable Long userId){
        List<PropertyDto> propertyDto = propertyService.findPastListing(userId);
        return new ResponseEntity<>(propertyDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyById(@PathVariable Long id) {
        propertyService.deleteById(id);
        return new ResponseEntity<>("Property Deleted Successfully", HttpStatus.OK);
    }

    @PostMapping("/{propertyId}/photos")
    public ResponseEntity<String> upload(@RequestParam(name = "photo") MultipartFile[] multipartFile, @PathVariable Long propertyId) {
        propertyPhotoService.updatePropertyPhoto(propertyId, multipartFile);
        return new ResponseEntity<>("Image uploaded successfully!", HttpStatus.OK);
    }

    @GetMapping("/{propertyId}/photos")
    public ResponseEntity<List<PropertyPhotoDto>> getPropertyPhotosByProperty(@PathVariable Long propertyId){
        List<PropertyPhotoDto> photos =  propertyPhotoService.findPropertyPhotoByProperty(propertyId);
        return new ResponseEntity<>(photos,HttpStatus.OK);
    }

    @GetMapping("/photos/{propertyPhotoId}")
    public ResponseEntity<PropertyPhotoDto> getOnePropertyPhoto(@PathVariable Long propertyPhotoId){
        PropertyPhotoDto propertyPhotoDto = propertyPhotoService.getOnePropertyPhoto(propertyPhotoId);
        return new ResponseEntity<>(propertyPhotoDto, HttpStatus.OK);
    }

    @DeleteMapping("/photos/{propertyPhototoId}")
    public ResponseEntity<String> deletePropertyPhoto(@PathVariable Long propertyPhototoId){
        propertyPhotoService.deletePropertyPhoto(propertyPhototoId);
        return new ResponseEntity<>("Photo successfully deleted", HttpStatus.OK);
    }

}
