package com.example.school.config;

import com.example.school.security.JwtAuthenticationEntryPoint;
import com.example.school.security.jwt.JwtConfigurer;
import com.example.school.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfigurer jwtConfigurer;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilterBean() {
        return new JwtTokenFilter();

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
                .antMatchers("/auth/**", "/api-docs/**", "/swagger-ui.html**").permitAll()
                .antMatchers("/users").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("ADMIN", "USER")
                .and()
                .apply(jwtConfigurer);

        httpSecurity.addFilterBefore(jwtTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();

    }
}
