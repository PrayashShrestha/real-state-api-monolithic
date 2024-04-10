package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoService {
    String saveProfilePhoto(MultipartFile multipartFile, User user);
}
