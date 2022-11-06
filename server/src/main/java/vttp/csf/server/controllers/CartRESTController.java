package vttp.csf.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRESTController {
    
    @PostMapping(path="/cart")
    public ResponseEntity<String> addToCart() {
        
        
        return null;
    }
}
