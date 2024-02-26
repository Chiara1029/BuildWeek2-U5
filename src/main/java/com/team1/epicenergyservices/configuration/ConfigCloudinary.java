package com.team1.epicenergyservices.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary uploader(@Value("${cloudinary.name}") String name,
                               @Value("${cloudinary.key}") String apikey,
                               @Value("${cloudinary.secret}") String secret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", apikey);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }

    @Bean
        // Bean che mi serve per configurare a piacimento la security filter chain
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disabilitare alcuni comportamenti di default
        httpSecurity.formLogin(AbstractHttpConfigurer::disable); // Non vogliamo il form di login
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Non vogliamo la protezione da CSRF
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Non vogliamo le sessioni

        // Aggiungere filtri custom
        //httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Aggiungere/rimuovere determinate regole di protezione per gli endpoint
        // Qui possiamo determinare se debba essere necessaria un'autenticazione per accedervi
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }
}
