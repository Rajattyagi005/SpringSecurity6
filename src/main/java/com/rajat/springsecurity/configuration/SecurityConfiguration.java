package com.rajat.springsecurity.configuration;

import com.rajat.springsecurity.exception.CustomAccessDeniedHandler;
import com.rajat.springsecurity.exception.CustomAuthenticationEntryPoint;
import com.rajat.springsecurity.exception.CustomAuthenticationFailureHandler;
import com.rajat.springsecurity.filter.CustomJWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;// we use this when unauthenticated user tries to access authenticated resource

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;// need to check this and custom filter with AuthenticationManager, we need this when during authentication there is some exception
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;// when authenticated user tries to access a resource which is not permitted to the user.

    @Autowired
    private CustomJWTFilter customJWTFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers("/welcome","/login","/register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(httpbasic->httpbasic.authenticationEntryPoint(customAuthenticationEntryPoint))
                .addFilterBefore(customJWTFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();


//                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(customAuthenticationEntryPoint)
//                        .accessDeniedHandler(customAccessDeniedHandler)
//                )
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
