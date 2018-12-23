package com.miluna.springvehicleguide.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


@Configuration
@Order(value = 1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;

    @Autowired
    public SecurityConfig(@Lazy JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // we use stateless session; session won't be used to store user's auth state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // allow CorsConfigurationSource Bean
                .cors()
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                // authorization requests config
                .authorizeRequests()

                // allow OPTIONS methods
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow all to retrieve data, register and log as Admin
                .antMatchers(HttpMethod.GET, "/**").permitAll()

                // allow for registering
                .antMatchers(HttpMethod.POST, "/users").permitAll()

                // allow login
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()

                // must be an admin if trying to modify data
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN").anyRequest().authenticated()
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN").anyRequest().authenticated()
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN").anyRequest().authenticated()
                .and().formLogin()
                ;
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
