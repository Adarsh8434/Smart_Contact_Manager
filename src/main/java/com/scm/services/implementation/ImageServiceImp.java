package com.scm.services.implementation;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helpers.AppConstants;
import com.scm.services.ImageService;

@Component
public class ImageServiceImp implements ImageService{

    
   private Cloudinary cloudinary;

    public ImageServiceImp(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
        // TODO Auto-generated method stub
      try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", filename));

            return this.getUrlFromPublicId(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // and return raha hoga : url

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUrlFromPublicId'");
    }

   

}
