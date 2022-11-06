package vttp.csf.server.controllers;

import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.server.exceptions.UserException;
import vttp.csf.server.models.User;
import vttp.csf.server.services.UserService;

@RestController
public class UserRESTController {
    
    @Autowired
    private UserService userSvc;

    @PostMapping(path="/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        
        System.out.println(">>>> payload check: " + user.getFirstName() + user.getLastName() + user.getEmail() + user.getMobile() + user.getPassword());
        String uuid = UUID.randomUUID().toString().substring(0,8);
        user.setId(uuid);
        JsonObject jsonObj; ;
        try {
            userSvc.createUser(user);
            System.out.println(">>>>> user created");
            jsonObj = Json.createObjectBuilder().add("message","SUCCESS! The user account has been created!").build();
            return ResponseEntity.status(HttpStatus.CREATED).body(jsonObj.toString());
            // return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS: The user account has been created!");
            
        } catch (UserException e) {
            System.out.println(">>>>> cannot create user");
            jsonObj = Json.createObjectBuilder().add("message","ERROR! %s".formatted(e.getReason())).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObj.toString());
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR! %s".formatted(e.getReason()));
            
        }

    }


}
