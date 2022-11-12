package vttp.csf.server.controllers;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.server.exceptions.UserException;
import vttp.csf.server.models.User;
import vttp.csf.server.repository.UserRepository;
import vttp.csf.server.services.UserService;
import vttp.csf.server.utility.ConversionUtil;

@RestController
public class UserRESTController {
    
    @Autowired
    private UserService userSvc;

    @Autowired
    private UserRepository userRepo;

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
