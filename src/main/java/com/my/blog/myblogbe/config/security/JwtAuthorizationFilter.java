package com.my.blog.myblogbe.config.security;

import com.my.blog.myblogbe.service.impl.api.JwtService;
import com.my.blog.myblogbe.service.impl.api.UserService;
import com.my.blog.myblogbe.service.impl.model.SecurityUserModel;
import com.my.blog.myblogbe.web.exceptions.model.IncorrectCredentialsException;
import com.my.blog.myblogbe.web.exceptions.model.JwtAuthenticationException;
import com.my.blog.myblogbe.web.exceptions.model.UserNotActivatedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/** Jwt authorization filter. */
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private static final String HEADER = "Authorization";
  private static final String PREFIX = "Bearer ";
  private final UserService userService;
  private final JwtService jwtService;

  /** Filter which checks if jwt token is presented and authenticates user. */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    getAndAuthenticateUserFromJwt(getAndValidateJwtToken(request));
    chain.doFilter(request, response);
  }

  /**
   * Method to extract Jwt from request.
   *
   * @param request HttpServletRequest.
   * @return Jwt token.
   */
  private String getAndValidateJwtToken(HttpServletRequest request) {
    String authenticationHeader = request.getHeader(HEADER);
    if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
      throw new JwtAuthenticationException(
          Map.of("token", "Authorization jwt token required"), "Jwt error");
    }
    String jwt = authenticationHeader.replace(PREFIX, "");
    if (!jwtService.isAuthorizationToken(jwt)) {
      throw new JwtAuthenticationException(
          Map.of("token", "Authorization jwt token required"), "Jwt error");
    }
    return jwt;
  }

  /**
   * Method to get user info from db using jwt and authenticate him.
   *
   * @param jwt Jwt token.
   */
  private void getAndAuthenticateUserFromJwt(String jwt) {
    try {
      SecurityUserModel securityUserModel =
          userService.getUserByEmail(jwtService.getEmailFromJwt(jwt));

      if (!securityUserModel.isEnabled()) {
        throw new UserNotActivatedException(
            Map.of("email", "Please activate your account through email link"),
            "User not activated");
      }
      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(
              securityUserModel, "", securityUserModel.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (IncorrectCredentialsException e) {
      throw new JwtAuthenticationException(
          Map.of("token", "Jwt verification error"),
          "Jwt error");
    }
  }
}
