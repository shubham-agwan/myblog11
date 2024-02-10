package com.myblog.myblog11.config;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().
                disable().
                authorizeRequests().
                antMatchers(HttpMethod.GET,"/api/**").permitAll().
                // this line permits all URL contains "/api/**"
                antMatchers(HttpMethod.POST,"/api/**").hasAnyRole("ADMIN","USER").
                antMatchers(HttpMethod.POST,"/auth/login/**").permitAll().
                //.hasAnyRole("ADMIN") this we can add here as well or we can add below annotation
                //@EnableGlobalMethodSecurity(prePostEnabled = true)
                //@PreAuthorize("hasRole('ADMIN')") this anotation on post or get or on any
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }

    //existing method from spring security to create user and password
    @Bean
    @Override
     protected  UserDetailsService userDetailsService(){
         UserDetails user1 =
                 User.builder().username("shubham").password(passwordEncoder()
                         .encode("shubham")).roles("USER").build();

        UserDetails user2  =
                User.builder().username("admin").password(passwordEncoder()
                        .encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2);
     }


}