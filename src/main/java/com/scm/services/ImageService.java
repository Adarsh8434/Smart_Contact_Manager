package com.scm.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface ImageService {

    String uploadImage(MultipartFile profileImage,String filename) throws IOException;
 
    String getUrlFromPublicId(String publicId);
}
