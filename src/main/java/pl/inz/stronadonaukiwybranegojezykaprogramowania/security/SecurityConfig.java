package pl.inz.stronadonaukiwybranegojezykaprogramowania.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    auth.requestMatchers("/api/auth/login", "/api/auth/register").permitAll();
                    auth.requestMatchers("/Courses/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/CodeAnalysis/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/VideoMetadata/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/Progress/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/DailyTask/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/DailyTaskResult/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/Question/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/Quiz/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/QuizResult/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/Lessons/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/Assignment/Submit").hasAnyRole("USER", "ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }
}
