package com.scm.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repository.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImp implements UserService {
    
    @Autowired
    private UserRepo userRepo;
    @Autowired 
    private PasswordEncoder passwordEncoder;
    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // user id: have to generate randomly
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        // password encode
        // user.setPassword(userId)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
      user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());
       return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user3=userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
        user3.setName(user.getName());
        user3.setEmail(user.getEmail());
        user3.setPassword(user.getPassword());
        user3.setAbout(user.getAbout());
        user3.setPhoneNumber(user.getPhoneNumber());
        user3.setProfilePic(user.getProfilePic());
       user3.setEmailVerified(user.isEmailVerified());
       user3.setPhoneVerified(user.isPhoneVerified());
       user3.setProvider(user.getProvider());
       user3.setProviderUserId(user.getProviderUserId());

    //    saving user in database

    User save=userRepo.save(user3);

    return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user3=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
        userRepo.delete(user3);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user3=userRepo.findById(userId).orElse(null);

        return user3!=null ? true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        return user!=null ? true:false;
    }

    @Override
    public List<User> getAllUsers() {
      return userRepo.findAll();
    }

   
}
