package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.*;
import miu.ea.realestateapimonolithic.mapper.PropertyMapper;
import miu.ea.realestateapimonolithic.model.PropertyPhoto;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import miu.ea.realestateapimonolithic.service.PropertyService;
import miu.ea.realestateapimonolithic.service.UserService;
import miu.ea.realestateapimonolithic.service.impl.CloudinaryServiceImpl;
import miu.ea.realestateapimonolithic.service.impl.PropertyPhotoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.USER_URL_PREFIX)
public class UserController {

    private final UserService userService;
    private final PropertyService propertyService;
    private final AgentReviewService agentReviewService;
    private final PropertyPhotoServiceImpl propertyPhotoService;

    // move to AuthController
//    @PostMapping
//    public void saveUser(@RequestBody AccountRegistrationRequest user) {
//         userService.saveUser(user);
//    }

    @GetMapping("/{userId}/property")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable long userId){
        List<PropertyDto> properties = propertyService.findAllByUserAndListingStatus(userId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable long id) {
        return userService.findUser(id);
    }

    @GetMapping
    public List<User> findAllUser(){
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}/update-password")
    public void updatePassword(@PathVariable long id, @RequestParam String oldPassword,@RequestParam String newPassword){
        userService.updatePassword(id, oldPassword, newPassword);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<AgentReviewDto>> getAllAgentReview(){
        List<AgentReviewDto> agentReviews = agentReviewService.getAllAgentReview();
        return new ResponseEntity<>(agentReviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{agentId}")
    public ResponseEntity<List<AgentReviewDto>> getAgentReviewById(@PathVariable Long agentId){
        List<AgentReviewDto> agentReviews = agentReviewService.getAgentReviewsByAgentId(agentId);
        return new ResponseEntity<>(agentReviews,HttpStatus.OK);
    }


    @GetMapping("/{userId}/deactivate")
    public ApiResponse<?> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ApiResponse.builder()
                .success(true)
                .build();
    }

//    @PostMapping("/{propertyId}/upload")
//    @ResponseBody
//    public ResponseEntity<String> upload(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable Long propertyId) throws IOException {
//        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
//        if (bi == null) {
//            return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
//        }
//        Map result = cloudinaryService.upload(multipartFile);
//        PropertyPhoto image = new PropertyPhoto();
//        image.setName((String) result.get("original_filename"));
//        image.setImageUrl((String) result.get("url"));
//        image.setImageId((String) result.get("public_id"));
//        image.setProperty(PropertyMapper.MAPPER.mapToProperty(propertyService.findById(propertyId)));
//        propertyPhotoService.savePropertyPhoto(image);
//        return new ResponseEntity<>("image ajoutée avec succès ! ", HttpStatus.OK);
//    }

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

