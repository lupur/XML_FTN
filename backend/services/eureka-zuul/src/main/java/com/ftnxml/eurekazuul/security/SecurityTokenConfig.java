package com.ftnxml.eurekazuul.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling()
                .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                // allow all who are accessing "user" service
                .antMatchers(HttpMethod.POST, "/user/**").permitAll().antMatchers("/soap/**").permitAll()
                // Any other request must be authenticated
//                .antMatchers("/**").permitAll();
                .antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/user/**")
                .hasRole("ADMIN").anyRequest().authenticated();

    }
}
