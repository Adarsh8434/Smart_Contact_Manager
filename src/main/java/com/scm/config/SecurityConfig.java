package com.scm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.implementation.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

  // user create and login using java code with in memory service
  // @Bean
  // public UserDetailsService userDetailsService(){
  // UserDetails user1=User.
  // withDefaultPasswordEncoder().
  // username("admin123").password("admin123").
  // roles("admin","user")
  // .build();

  // UserDetails user2=User.withUsername("user123").password("password").build();
  // var inMemoryUserDetailsManager =new InMemoryUserDetailsManager(user1,user2);
  // return inMemoryUserDetailsManager;
  // }
 
  @Autowired
  OAuthAuthenicationSuccessHandler oAuthAuthenicationSuccessHandler;
  @Autowired
  private SecurityCustomUserDetailService userDetailService;

  // configuration of authentication provider for spring security
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    // User detail service ka object
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    // password encoder ka object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    // configuration
    // url configure kiya hai ki kaun se public rahenge kaun se nahi
    httpSecurity.authorizeHttpRequests(authorize -> {
      // authorize.requestMatchers("/home","/register","/services").permitAll();
      authorize.requestMatchers("/user/dashboard").hasRole("USER");
      authorize.anyRequest().permitAll();
    });

    // form default login if i need to change releated to login form i will there
    // httpSecurity.formLogin(Customizer.withDefaults());
    httpSecurity.formLogin(form ->{
    form.loginPage("/login");
     form.loginProcessingUrl("/authenticate");
     form.successForwardUrl("/user/profile");
     form.failureForwardUrl("/login?error=true");
     form.usernameParameter("email");
     form.passwordParameter("password");
     form.failureHandler(new AuthenticationFailureHandler(){

      @Override
      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
        response.sendRedirect("/login?error=true");
      }
           
     });
      form.successHandler(new AuthenticationSuccessHandler() {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
          // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
          response.sendRedirect("/user/dashboard");
        }
        
      });
    // Allow everyone to access the login page
   
  });

  httpSecurity.csrf(AbstractHttpConfigurer::disable);
  httpSecurity.logout(logoutForm->{ logoutForm.logoutUrl("/logout");
});

httpSecurity.oauth2Login(oauth->{
  oauth.loginPage("/login")
  .successHandler(oAuthAuthenicationSuccessHandler)
  .failureUrl("/login?error=true") ;
});

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
