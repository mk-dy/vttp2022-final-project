package vttp.csf.server.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.server.exceptions.UserException;
import vttp.csf.server.models.User;
import vttp.csf.server.models.UserDetailsImpl;
import vttp.csf.server.payload.response.JwtResponse;
import vttp.csf.server.repository.UserRepository;
import vttp.csf.server.services.UserService;
import vttp.csf.server.utility.ConversionUtil;
import vttp.csf.server.utility.JWTUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userSvc;
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        // email used in place of username
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, 
            userDetails.getId(), 
            userDetails.getEmail()));

    }

    @PostMapping(path="/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        
        String uuid = UUID.randomUUID().toString().substring(0,8);
        System.out.println(">>> check before encoded password: " + user.getPassword());
        user.setId(uuid);

        System.out.println(">>>> payload check: " + user.getFirstName() + user.getLastName() + user.getEmail() + user.getMobile() + user.getPassword());
        
        JsonObject jsonObj;
        try {
            userSvc.createUser(user);
            System.out.println(">>>>> user created");
            jsonObj = Json.createObjectBuilder().add("message","SUCCESS! The user account has been created!").build();
            return ResponseEntity.status(HttpStatus.CREATED).body(jsonObj.toString());
            // return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS: The user account has been created!");
            
        } catch (UserException e) {
            System.out.println(">>>>> cannot create user");
            jsonObj = Json.createObjectBuilder().add("message","ERROR! %s".formatted(e.getMessage())).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObj.toString());
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR! %s".formatted(e.getReason()));
            
        }

    }

    @GetMapping(path="/getUser/{email}")
    public ResponseEntity<String> getUser(@PathVariable String email) {
        System.out.println(">>> email: " + email);
        User user = new User();
        
        Optional<User> optUser = userRepo.getUserByEmail(user.getEmail());
        if (optUser.isEmpty()) {
            System.out.println("EMPTYYY");
        }
        user = optUser.get();

        System.out.println(user.toString());
        JsonObject jObject = ConversionUtil.userToJson(user);
        
         return ResponseEntity.status(HttpStatus.OK).body(jObject.toString());
    }

    
}
