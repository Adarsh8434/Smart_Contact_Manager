package com.scm.controller;

import java.time.LocalDate;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.message;
import com.scm.helpers.messagetype;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class pagecontroller {

   @Autowired
   private UserService userService;
     @GetMapping("/")
   public String index(){
    return "redirect:home";
   }


    @RequestMapping("/home")
   public String home(Model model){
    model.addAttribute("title", "My Page Title");
    model.addAttribute("content", "home");
    System.out.println("home page loader");
    return 
    "home";
   }
   @RequestMapping("/about")
   public String aboutPage(Model model){
    model.addAttribute("date", LocalDate.now());
    System.out.println("About page loading");
    return "about";
   }
   @RequestMapping("/services")
   public String servicesPage(){
     System.out.println("Service page is loaded");
     return "service";
   }
   @RequestMapping("/contact")
   public String contactPage(){
     System.out.println("Contact page is loaded");
     return "contact";
   }

  //  This is registration controller
   @GetMapping("/login")
   public String LoginPage(Model model) {
    UserForm userform=new UserForm();
    model.addAttribute("userform", userform);
    System.out.println("Login page is loaded");
       return "login";
   }
   @GetMapping("/register")
   public String RegisterPage(Model model) {
    System.out.println("Registration page loaded");
    UserForm userform=new UserForm();
    model.addAttribute("userform", userform);
       return "register";
   }
  //  processing register
@RequestMapping(value = "/do-register", method=RequestMethod.POST)
public String ProcessRegister(@Valid @ModelAttribute UserForm userform,BindingResult rBind,HttpSession session, Model model) {
  System.out.println("User registration successfully");
  // fetch
System.out.println(userform);
// System.out.println("Error are "+rBind.getAllErrors());
// validate
if (rBind.hasErrors()) {
  rBind.getAllErrors().forEach(error -> {
    System.out.println("Error: " + error.getDefaultMessage());
});
  model.addAttribute("userform", userform);
  System.out.println("got error in form");
  return "register"; 
}

// save database

// user service

// Userform se user banaya hai maine
// User user=User.builder()
// .name(userform.getName())
// .email(userform.getEmail())
// .password(userform.getPassword())
// .phoneNumber(userform.getPhoneNumber())
// .about(userform.getAbout())
// .profilePic("https://gravatar.com/avatar/27205e5c51cb03f862138b22bcb5dc20f94a342e744ff6df1b8dc8af3c865109.jpg").
// build();
// User saveUser=userService.saveUser(user);

User user=new User();
user.setName(userform.getName());
user.setEmail(userform.getEmail());
user.setAbout(userform.getAbout());
user.setPassword(userform.getPassword());
user.setPhoneNumber(userform.getPhoneNumber());
user.setProfilePic("https://gravatar.com/avatar/27205e5c51cb03f862138b22bcb5dc20f94a342e744ff6df1b8dc8af3c865109.jpg");
User saveUser=userService.saveUser(user);
System.out.println("User saved");


// add the message

message messag=message.builder().content("Registration Successful").type(messagetype.blue).build();
session.setAttribute("message",messag);

    return "redirect:/register";
}

   
}
