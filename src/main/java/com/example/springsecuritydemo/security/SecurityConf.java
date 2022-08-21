package com.example.springsecuritydemo.security;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@EnableSpringSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @PostConstruct
    public void setup() {
        userRepository.save(new UserEntity(1,"iriela@gmail.com",
                passwordEncoder.encode("cheers@123"),"ROLE_ADMIN"));
        userRepository.save(new UserEntity(2,"jade@yahoo.com",
                passwordEncoder.encode("jade@123"),"ROLE_USER"));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setPasswordEncoder(password);
        daoAuthProvider.setUserDetailsService(userDetails);
        auth.authenticationProvider(daoAuthProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/any/**").hasAnyRole("ADMIN","USER")
                .anyRequest()
                .authenticated()
                .and().formLogin().disable();
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();
    }

}
