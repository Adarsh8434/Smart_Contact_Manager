package com.scm.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){
        // when we login using email
        if(authentication instanceof OAuth2AuthenticationToken){
  
            var aOAuth2AuthenticationToken=(OAuth2AuthenticationToken) authentication;
            var clientid=aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User=(OAuth2User)authentication.getPrincipal();
            String username="";
            // when we login using google
        if(clientid.equalsIgnoreCase("google")){
            System.out.println("Getting email from google");
          username= oauth2User.getAttribute("email");

        }
        // when we login using github
        else  if(clientid.equalsIgnoreCase("github")){
            System.out.println("Getting email from github");
            username=oauth2User.getAttribute("email")!=null?oauth2User.getAttribute("email").toString():oauth2User.getAttribute("login").toString()+"@github.com";
            oauth2User.getAttribute("email");

        }
        System.out.println("Final extracted username: " + username);
     return username;
        }else{
            System.out.println("Getting data from local database");
        return authentication.getName();
}    }
}
