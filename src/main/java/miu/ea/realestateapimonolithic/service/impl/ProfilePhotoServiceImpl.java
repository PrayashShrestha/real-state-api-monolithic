package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.exception.InvalidInputException;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.service.ProfilePhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfilePhotoServiceImpl implements ProfilePhotoService {
    private final CloudinaryServiceImpl cloudinaryService;
    @Override
    public String saveProfilePhoto(MultipartFile multipartFile, User user) {
        try{
            Map clodinaryResult = cloudinaryService.upload(multipartFile);
            return (String) clodinaryResult.get("url");
        }catch(IOException e){
            throw new InvalidInputException("Image not valid");
        }
    }
}
