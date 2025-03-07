package com.scm.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helpers.helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {
      
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;


  // Used this to make all the path of user to access model username etc after Authentication

     @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
         if(authentication==null) return;
        System.out.println("Adding loggged in user information to the model");
       
        String username= helper.getEmailOfLoggedInUser(authentication);
        logger.info("user logged in : {} ",username);

        User user=userService.getUserByEmail(username);
            System.out.println(user);
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user);
        
     
    }
}
