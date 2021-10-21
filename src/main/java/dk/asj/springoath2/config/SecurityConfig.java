package dk.asj.springoath2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain configure(HttpSecurity http) throws Exception{
    return http
      // Basic setup to have one open endpoint without authentication, and one requiring a specific authority role
      .authorizeRequests(auth -> {
        auth.mvcMatchers("/").permitAll();
        auth.antMatchers("/**").hasAuthority("ROLE_USER");
      })
      .build();
  }
}
