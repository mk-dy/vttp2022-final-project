// package vttp.csf.server.utility;

// import static org.junit.jupiter.api.DynamicTest.stream;

// import java.nio.file.attribute.UserPrincipal;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.stream.Collectors;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTVerificationException;

// import vttp.csf.server.constant.SecurityConstant;

// public class JWTTokenProvider {
    
//     @Value("${jwt.secret}")
//     private String secret;

    
//     // public String generateJwtToken(UserPrincipal userPrincipal) {
//     //     String[] claims = getClaimsFromUser(userPrincipal);
//     //     return JWT.create()
//     //         .withIssuer(SecurityConstant.ISAAC)
//     //         .withIssuedAt(new Date())
//     //         .withSubject(userPrincipal.getUsername()) // for the user -> username or userid
//     //         .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
//     //         .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
//     //         .sign(Algorithm.HMAC512(secret.getBytes()));
//     // };

//     // private String[] getClaimsFromUser(UserPrincipal userPrincipal) {

//     // }

//     // upon passing authentication, give user the token
//     public String generateJwtToken(UserPrincipal userPrincipal) {
//         String[] claims = getClaimsFromUser(userPrincipal);
//         return JWT.create().withIssuer(SecurityConstant.ISAAC).withAudience(GET_ARRAYS_ADMINISTRATION)
//                 .withIssuedAt(new Date(System.currentTimeMillis())).withSubject(userPrincipal.getUsername())
//                 .withArrayClaim(AUTHORITIES, claims).withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
//                 .sign(HMAC512(secret.getBytes()));
//     }

//     public List<GrantedAuthority> getAuthorities(String token) {
//         String[] claims = getClaimsFromToken(token);
//         return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//     }

//     public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
//         UsernamePasswordAuthenticationToken userPasswordAuthToken = new
//                 UsernamePasswordAuthenticationToken(username, null, authorities);
//         userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//         return userPasswordAuthToken;
//     }

//     public boolean isTokenValid(String username, String token) {
//         JWTVerifier verifier = getJWTVerifier();
//         return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
//     }

//     public String getSubject(String token) {
//         JWTVerifier verifier = getJWTVerifier();
//         return verifier.verify(token).getSubject();
//     }

//     private boolean isTokenExpired(JWTVerifier verifier, String token) {
//         Date expiration = verifier.verify(token).getExpiresAt();
//         return expiration.before(new Date());
//     }

//     private String[] getClaimsFromToken(String token) {
//         JWTVerifier verifier = getJWTVerifier();
//         return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
//     }

//     private JWTVerifier getJWTVerifier() {
//         JWTVerifier verifier;
//         try {
//             Algorithm algorithm = HMAC512(secret);
//             verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
//         }catch (JWTVerificationException exception) {
//             throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
//         }
//         return verifier;
//     }

//     private String[] getClaimsFromUser(UserPrincipal user) {
//         List<String> authorities = new ArrayList<>();
//         for (GrantedAuthority grantedAuthority : user.getAuthorities()){
//             authorities.add(grantedAuthority.getAuthority());
//         }
//         return authorities.toArray(new String[0]);
//     }
// }
