package com.my.blog.myblogbe.config.security;


import com.my.blog.myblogbe.service.api.JwtService;
import com.my.blog.myblogbe.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Configuration class for web security. */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final UserService userService;
  private final JwtService jwtService;
  private final FilterChainExceptionHandler filterChainExceptionHandler;

  /** Bean with SecurityFilterChain configuration */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/a/rest/testController/admin/**")
        .hasRole("ADMIN")
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/a/rest/testController/user/**")
        .hasRole("USER")
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/a/rest/testController/openai/**")
        .hasRole("USER")
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .addFilterBefore(
            new JwtAuthorizationFilter(userService, jwtService),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(filterChainExceptionHandler, JwtAuthorizationFilter.class);
    return http.build();
  }

  /** Bean with WebSecurityCustomizer configuration */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.ignoring()
            .requestMatchers(
                "/a/rest/accounts/**",
                "/a/swagger-ui/**",
                "/a/rest/testController/test/**",
                "/a/v3/api-docs/**");
  }
}
