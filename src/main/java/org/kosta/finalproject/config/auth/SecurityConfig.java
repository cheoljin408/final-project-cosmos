package org.kosta.finalproject.config.auth;

import lombok.RequiredArgsConstructor;
import org.kosta.finalproject.model.domain.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**", "/login").permitAll()
                .antMatchers("/study/**").hasRole(Role.GUEST.name())
                .anyRequest().authenticated()
                .and()
                .oauth2Login().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
