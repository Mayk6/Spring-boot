package my.homework.security;

import my.homework.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(registry -> registry
//                        .requestMatchers("api/*").permitAll()
//                        .requestMatchers("/ui/issues").hasAuthority(String.valueOf(Role.ROLE_ADMIN))
//                        .requestMatchers("/ui/reader/**").hasAnyAuthority(String.valueOf(Role.ROLE_READER))
//                        .requestMatchers("/ui/books").authenticated()
//                        .anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }
        @Bean
        SecurityFilterChain testSecurityFilterChain (HttpSecurity security) throws Exception {
            return security.authorizeHttpRequests(registry -> registry
                            .anyRequest().permitAll())
                    .csrf(AbstractHttpConfigurer::disable)
                    .build();
        }
}
