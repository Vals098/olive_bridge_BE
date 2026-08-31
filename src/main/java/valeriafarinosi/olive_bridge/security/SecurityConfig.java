package valeriafarinosi.olive_bridge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JWTFilter jwtFilter
    ) throws Exception {

        httpSecurity.formLogin(AbstractHttpConfigurer::disable);

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.sessionManagement(
                sessions -> sessions.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        );

        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(req -> req
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
        );

        httpSecurity.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
