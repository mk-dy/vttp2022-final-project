package vttp.csf.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vttp.csf.server.models.ConversionUtil;
import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.repository.SearchRepository;
import vttp.csf.server.services.ProductService;

@RestController
public class CartRESTController {
    
    @Autowired
    private ProductService productSvc;

    @PostMapping(path="/cart")
    public ResponseEntity<String> addToCart(@RequestBody FinalProduct payload) {
        
        ConversionUtil.replaceNull(payload);
        System.out.println(">>>> payload: " + payload.toString());

        String message = productSvc.createFinalProduct(payload);
        System.out.println(">>>>> message: " + message);
        
        return null;
    }

    @GetMapping(path="/cart")
    public ResponseEntity<String> showCart() {
        


        // ConversionUtil.replaceNull(payload);
        // System.out.println(">>>> payload: " + payload.toString());

        // String message = productSvc.createFinalProduct(payload);
        // System.out.println(">>>>> message: " + message);
        
        return null;
    }

}
