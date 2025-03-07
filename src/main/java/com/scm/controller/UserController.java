package com.scm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.helpers.helper;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {


    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    // Used this to make all the path of user to access model username etc after Authentication
  
    //  User dashbaord Page

    @RequestMapping(value="/dashboard",method= RequestMethod.GET)
        public String userDashboard(){
            System.out.println("user dashboard showing");
            return "user/dashboard";
        }

    // user profile page

    @RequestMapping(value="/profile",method= RequestMethod.GET)
        public String userProfile(Authentication authentication,Model model){
            return "user/profile";
        }
//         @RequestMapping(value = "/profile", method = RequestMethod.GET)
// public String userProfile(Authentication authentication, Model model) {
//     String username = helper.getEmailOfLoggedInUser(authentication);
    
//     logger.info("User logged in: {}", username); // ✅ Fix Logging Issue
//     System.out.println("User profile showing for: " + username);
    
//     model.addAttribute("username", username); // ✅ Pass username to the UI
//     return "user/profile"; // ✅ Return the view
// }



    // User add contacts Page

    // User view contacts

    // user edit contacts

    // user delete contacts

    // user search contact

}
