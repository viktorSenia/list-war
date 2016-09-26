package com.leo.test.list.war.config;

import com.leo.test.list.war.model.Role;
import com.leo.test.list.war.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/registration", "/login", "/js/**", "/css/**", "/favicon.ico").permitAll().
                //                antMatchers("/admin/**").hasRole("SUMERADMIN").
                        antMatchers("/**").hasRole("USER").
                and().formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/youtube/list", true).
                and().logout().logoutSuccessUrl("/login?logout");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").
                and().withUser("admin").password("admin_password").roles("ADMIN");
    }

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public RoleHierarchy hierarchyImpl() {
        RoleHierarchyImpl hierarchyImpl = new RoleHierarchyImpl();
        for (String string : Role.hierarchy())
            hierarchyImpl.setHierarchy(string);
        return hierarchyImpl;
    }
}