package com.ubbdevs.studyit.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ResourceServerConfigurer {

    private static final String RESOURCE_ID = "resource_id";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID)
                .stateless(false);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs**").permitAll()
                .antMatchers("/swagger.json").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/departments/**").permitAll()
                .antMatchers("/semester/completion-percentage").permitAll()
                .antMatchers(HttpMethod.POST, "/users/student").permitAll()
                .antMatchers("/assignments").permitAll()
                .antMatchers("/timetable").permitAll()
                .antMatchers("/timetable/subject").permitAll()
                .antMatchers("/users/student/**").hasAuthority("STUDENT")
                .antMatchers("/users/professor/**").hasAuthority("PROFESSOR")
                .antMatchers("/students/**").hasAuthority("STUDENT")
                .antMatchers(HttpMethod.POST, "/news").hasAuthority("PROFESSOR")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
