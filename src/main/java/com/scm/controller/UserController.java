package com.scm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {


    private Logger logger=LoggerFactory.getLogger(UserController.class);
    //  User dashbaord Page

    @RequestMapping(value="/dashboard",method= RequestMethod.GET)
        public String userDashboard(){
            System.out.println("user dashboard showing");
            return "user/dashboard";
        }

    // user profile page

    @RequestMapping(value="/profile",method= RequestMethod.GET)
        public String userProfile(Principal principal){
            String name=principal.getName();
            logger.info("user logged in : ",name);
            System.out.println("user profile showing");
            return "user/profile";
        }


    // User add contacts Page

    // User view contacts

    // user edit contacts

    // user delete contacts

    // user search contact

}
