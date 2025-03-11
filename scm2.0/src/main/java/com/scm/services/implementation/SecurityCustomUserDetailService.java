package com.scm.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.repository.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user with username: " + username);
       User user=userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with this email id"+username));
       System.out.println("User roles: " + user.getAuthorities());
       return user;
    }

}
