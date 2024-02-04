package my.homework.security;

import my.homework.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class SecurityConfiguration {

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/ui/issues", "/issue/**").hasAuthority(String.valueOf(Role.ROLE_ADMIN))
                        .requestMatchers("/ui/reader/**", "/reader/**").hasAnyAuthority(String.valueOf(Role.ROLE_READER))
                        .requestMatchers("/ui/books", "/book/**").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
