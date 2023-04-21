package com.my.blog.myblogbe.service.impl;

import com.my.blog.myblogbe.service.api.JwtService;
import com.my.blog.myblogbe.service.model.enums.JwtTypeEnum;
import com.my.blog.myblogbe.web.exceptions.model.JwtAuthenticationException;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import jakarta.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
  private final byte[] secretKey = System.getenv("MyBlogJwtSecret").getBytes();
  private final ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor =
      new DefaultJWTProcessor<>();

  @PostConstruct
  public void init() {
    JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<>(secretKey);
    JWEKeySelector<SimpleSecurityContext> jweKeySelector =
        new JWEDecryptionKeySelector<>(
            JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
    jwtProcessor.setJWEKeySelector(jweKeySelector);
  }

  @Override
  public String createJwt(String email, JwtTypeEnum type) {
    try {
      Date now = new Date();

      JWTClaimsSet claims =
          new JWTClaimsSet.Builder()
              .claim("email", email)
              .claim("type", type)
              .expirationTime(new Date(now.getTime() + 1000 * 60 * 24 * 10)) // expires in 10 days
              .notBeforeTime(now)
              .build();

      Payload payload = new Payload(claims.toJSONObject());

      JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);

      DirectEncrypter encrypter = new DirectEncrypter(secretKey);

      JWEObject jweObject = new JWEObject(header, payload);
      jweObject.encrypt(encrypter);
      return jweObject.serialize();
    } catch (JOSEException e) {
      throw new JwtAuthenticationException(
          Map.of("jwt_unknown", "Jwt creation error"), "Jwt unknown error");
    }
  }

  @Override
  public String getEmailFromJwt(String jwt) {
    return (String) getJWTClaimsSetFromToken(jwt).getClaim("email");
  }

  @Override
  public Boolean isAuthorizationToken(String jwt) {
    return getJWTClaimsSetFromToken(jwt).getClaim("type").equals(JwtTypeEnum.AUTHORIZATION.name());
  }

  @Override
  public Boolean isEmailActivationToken(String jwt) {
    return getJWTClaimsSetFromToken(jwt)
        .getClaim("type")
        .equals(JwtTypeEnum.EMAIL_ACTIVATION.name());
  }

  @Override
  public Boolean isPasswordForgottenToken(String jwt) {
    return getJWTClaimsSetFromToken(jwt)
        .getClaim("type")
        .equals(JwtTypeEnum.PASSWORD_FORGOT.name());
  }

  private JWTClaimsSet getJWTClaimsSetFromToken(String jwt) {
    try {
      return jwtProcessor.process(jwt, null);
    } catch (BadJOSEException e) {
      throw new JwtAuthenticationException(Map.of("token", e.getMessage()), "Jwt error");
    } catch (ParseException | JOSEException e) {
      throw new JwtAuthenticationException(
          Map.of("jwt_unknown", "Jwt verification error"), "Jwt unknown error");
    }
  }
}
