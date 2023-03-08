package com.fastcampus.ecommerce.admin.config;

import com.fastcampus.ecommerce.admin.service.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String DEFAULT_HOME_URL = "/";

    @Autowired
    AdminUserDetailService adminUserDetailService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/img/**", "/js/**", "/css/**", "/scss/**", "/vendor/**",
                        "/users/register", "/error"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl(DEFAULT_HOME_URL)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl(DEFAULT_HOME_URL);
    }
    @Bean
    public PasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService).passwordEncoder(bPasswordEncoder());
    }
}
