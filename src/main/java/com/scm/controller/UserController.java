package com.scm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.repository.UserRepo;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    private Logger logger=LoggerFactory.getLogger(UserController.class);
    //  User dashbaord Page

    @RequestMapping(value="/dashboard",method= RequestMethod.GET)
        public String userDashboard(){
            System.out.println("user dashboard showing");
            return "user/dashboard";
        }

    // user profile page

    @RequestMapping(value="/profile",method= RequestMethod.GET)
        public String userProfile(Model model,Principal principal,Authentication authentication){
            String name=Helper.getEmailOfLoggedInUser(authentication);
           
            logger.info("user logged in : ",name);
         User user=  userService.getUserByEmail(name); 
            System.out.println("user profile showing"+user.getEmail());
            model.addAttribute("loggedInUser", user);
            return "user/profile";
        }
  

    // User add contacts Page

    // User view contacts

    // user edit contacts

    // user delete contacts

    // user search contact

}
