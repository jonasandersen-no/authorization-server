package com.bjoggis.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthorizationServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthorizationServerApplication.class, args);
  }

  @Bean
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    return new InMemoryUserDetailsManager(
        User.withDefaultPasswordEncoder()
            .username("bjoggis")
            .password("pw").build());
  }

}
