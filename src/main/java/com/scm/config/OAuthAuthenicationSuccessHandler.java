package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler  {


    @Autowired
private  UserRepo userRepo;
    Logger logger=LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                

        logger.info("onAuthenticationSuccess");

        // identify the provider
        var oauthAuthenticationToken=(OAuth2AuthenticationToken)authentication;

        String authorizedClientRegistrationId=oauthAuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);
var oauthUser=(DefaultOAuth2User) authentication.getPrincipal();
oauthUser.getAttributes().forEach((key,value)->{
   logger.info(key+":"+value);
});

    User user1=new User();
   
    user1.setUserId(UUID.randomUUID().toString());
    user1.setRoleList(List.of(AppConstants.ROLE_USER));
    user1.setEnabled(true);
    user1.setEmailVerified(true);
    user1.setPassword("dummy");



if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
    user1.setEmail(oauthUser.getAttribute("email").toString());
    user1.setProfilePic(oauthUser.getAttribute("picture").toString());
    user1.setProviderUserId(oauthUser.getName());
    user1.setProvider(Providers.GOOGLE);
    user1.setAbout("This account is crested using google");

}else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){

// String email=oauthUser.getAttribute("email") !=null ?oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("Login").toString()+"@gmail.com";

// String picture=oauthUser.getAttribute("avatar_url").toString();
// String name=oauthUser.getAttribute("login").toString();
String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@gmail.com";
String picture = oauthUser.getAttribute("avatar_url") != null ? oauthUser.getAttribute("avatar_url").toString() : "";
String name = oauthUser.getAttribute("login") != null ? oauthUser.getAttribute("login").toString() : "Unknown";
String providerUserId=oauthUser.getName();


user1.setEmail(email);
user1.setProfilePic(picture);
user1.setName(name);
user1.setProviderUserId(providerUserId);
user1.setProvider(Providers.GITHUB);
// user1.setPassword("dummy");
user1.setAbout("This account is crested using github");

}else{
    logger.info("OAuthAuthenticationSuccessHandler: Unknown provider");
}
//         DefaultOAuth2User user=(DefaultOAuth2User) authentication.getPrincipal();
//         logger.info(user.getName());

//         user.getAttributes().forEach((key,value)->{ logger.info("{}=> {}",key,value);

//     });
//     logger.info(user.getAuthorities().toString());


//     String email=user.getAttribute("email").toString();
//     String name=user.getAttribute("name").toString();
//     String picture=user.getAttribute("picture").toString();

//     // create user and save in direction

 
//     user1.setProvider(Providers.GOOGLE);
//     user1.setEnabled(true);
//     user1.setEmailVerified(true);
//     user1.setProviderUserId(user.getName());
//     user1.setRoleList(List.of(AppConstants.ROLE_USER));
//     user1.setAbout("This account is created using google");


User user2=userRepo.findByEmail(user1.getEmail()).orElse(null);
if(user2==null){
    userRepo.save(user1);
    logger.info("user save");
}

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile") ;




    }


}

