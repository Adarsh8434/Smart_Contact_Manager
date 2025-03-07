package com.scm.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.Contactform;
import com.scm.helpers.helper;
import com.scm.helpers.message;
import com.scm.helpers.messagetype;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

Logger logger=org.slf4j.LoggerFactory.getLogger(ContactController.class);
    @Autowired
    ContactService contactService;

    @Autowired
    ImageService imageService;
    @Autowired
    private UserService userService;
    // add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){
        Contactform contactform=new Contactform();
        // contactform.setName("Adarsh");
        // contactform.setFavourite(true);
        // contactform.setAddress("Gadra near vijay");
        // contactform.setDescription("I am testing");
        // contactform.setEmail("rohi@gmail.com");
        model.addAttribute("contactform", contactform);
        
        return "user/add_contact";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute Contactform contactform,BindingResult result,Authentication authentication,HttpSession session) throws IOException{
      
 String filename=UUID.randomUUID().toString();

        String fileURL=imageService.uploadImage(contactform.getProfileImage(),filename);
         System.out.println(fileURL);
        // validation
        if(result.hasErrors()) {
            session.setAttribute("message", message.builder().content("Please correct the following errors").type(messagetype.red).build());
            return "user/add_contact";}
            String username="";
if(authentication!=null)
      {  
            System.out.println("Authentication is null in contact controller");

        username=helper.getEmailOfLoggedInUser(authentication);}

       // form ---> contact
       User user=userService.getUserByEmail(username);


       //Image Process

       logger.info("file information :{}",contactform.getProfileImage().getOriginalFilename());
        Contact contact=new Contact();
        contact.setName(contactform.getName());
        contact.setDescription(contact.getDescription());
        contact.setAddress(contact.getAddress());
        contact.setEmail(contactform.getEmail());
        contact.setLinkedInLink(contactform.getLinkedInLink());
        contact.setWebsiteLink(contactform.getWebsiteLink());
        contact.setPhoneNumber(contactform.getPhoneNumber());
        contact.setFavourite(contactform.isFavourite());
        contact.setUser(user);
        contact.setCloudinaryImagePublicId(filename);
        contact.setPicture(fileURL);
      

        contactService.save(contact);

        session.setAttribute("message", message.builder().content("Contact added successfully").type(messagetype.green).build());
        return "redirect:/user/contacts/add";
    }

    // view contacts


    @RequestMapping
    public String viewContacts(
    @RequestParam(value = "page",defaultValue ="0" ) int page,@RequestParam(value="size",defaultValue = "5") int size,
    @RequestParam(value="sortBy",defaultValue = "name") String sortBy,
    @RequestParam(value="direction" ,defaultValue="asc") String direction,
    Model model,Authentication authentication){
  if(authentication==null) {
    System.out.println("Authentication is null in contact controller");
    return "";}

        String username=helper.getEmailOfLoggedInUser(authentication);
        
       User user=userService.getUserByEmail(username);
        // load all the user
       Page<Contact> pageContact= contactService.getByUser(user,page,size,sortBy,direction);
       model.addAttribute("pagecontact", pageContact);
       
        return "user/contacts";
    }

}
