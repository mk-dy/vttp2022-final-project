// package vttp.csf.server.filter;

// import com.supportportal.utility.JWTTokenProvider;

// import vttp.csf.server.constant.SecurityConstant;

// import static org.springframework.http.HttpHeaders.AUTHORIZATION;
// import static org.springframework.http.HttpStatus.OK;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.List;



// @Component
// public class JwtAuthorizationFilter extends OncePerRequestFilter {
//     private JWTTokenProvider jwtTokenProvider;

//     public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
//         String authorizationHeader = request.getHeader(AUTHORIZATION);
//         if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) { // this is when you are not trying to get authorization
//             filterChain.doFilter(request, response);
//             return;
//         }
//         // otherwise, we will get the token
//         String token = authorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length()); // remove the "Bearer ", leaving only the token
//         String username = jwtTokenProvider.getSubject(token);

//         // make sure that token is valid                             and also make sure that the authentication is not already present in the security context
//         if (jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
//             List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
//             Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
//             SecurityContextHolder.getContext().setAuthentication(authentication); // set authentication into the security context as the user is now authenticated
//         } else {
//             SecurityContextHolder.clearContext();
//         }
        
//         filterChain.doFilter(request, response); // we pass and let the request continue its course
//     }
// }
