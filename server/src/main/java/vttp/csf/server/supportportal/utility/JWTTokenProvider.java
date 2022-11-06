package vttp.csf.server.supportportal.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import vttp.csf.server.supportportal.constant.SecurityConstant;

public class JWTTokenProvider {
    
    @Value("${jwt.secret}")
    private String secret;

    // upon passing authentication, give them the token
    // public String generateJwtToken(UserPrincipal userPrincipal) {
    //     String[] claims = getClaimsFromUser(userPrincipal);
    //     return JWT.create()
    //         .withIssuer(SecurityConstant.ISAAC)
    //         .withIssuedAt(new Date())
    //         .withSubject(userPrincipal.getUsername()) // for the user -> username or userid
    //         .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
    //         .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
    //         .sign(Algorithm.HMAC512(secret.getBytes()));
    // };

    // private String[] getClaimsFromUser(UserPrincipal userPrincipal) {

    // }
}
