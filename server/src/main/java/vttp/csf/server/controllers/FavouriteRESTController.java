package vttp.csf.server.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.repository.SearchRepository;
import vttp.csf.server.services.ProductService;
import vttp.csf.server.utility.ConversionUtil;

@RestController
public class FavouriteRESTController {
    
    @Autowired
    private ProductService productSvc;

    @PostMapping(path="/favourites")
    public ResponseEntity<String> addToCart(@RequestBody FinalProduct payload) {
        
        ConversionUtil.replaceNull(payload);
        System.out.println(">>>> payload: " + payload.toString());

        // to create final product and add to final_product table
        String message = productSvc.createFinalProduct(payload);
        System.out.println(">>>>> message: " + message);
        
        if (message.contains("Success")) {
            JsonObject jsonObj = Json.createObjectBuilder().add("message","Added").build();
            return ResponseEntity.status(HttpStatus.OK).body(jsonObj.toString());
        } else {
            JsonObject jsonObj = Json.createObjectBuilder().add("message","Not Added").build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObj.toString());
        }     
    }

    // @GetMapping(path="/cart")
    // public ResponseEntity<String> showCart() {
        
    //     /// for now use test's user id
    //     String userId = "12345678";
    //     List<FinalProduct> productList = new LinkedList<>();
    //     productList = this.productSvc.getFinalProductByUserId(userId);
    //     System.out.println(productList.get(0).getProdId());

        
    //     JsonArray jsonArr = ConversionUtil.finalProdtoJsonArr(productList);
        
    //     // ConversionUtil.replaceNull(payload);
    //     // System.out.println(">>>> payload: " + payload.toString());

    //     // String message = productSvc.createFinalProduct(payload);
    //     System.out.println(">>>>> the jsonarray: " + jsonArr.toString());
        
    //     return ResponseEntity.status(HttpStatus.OK).body(jsonArr.toString()); 
    // }

    @GetMapping(path="/favourites/{userId}")
    public ResponseEntity<String> showCart(@PathVariable String userId) {
        
        /// for now use test's user id
        // String userId = "12345678";
        List<FinalProduct> productList = new LinkedList<>();
        productList = this.productSvc.getFinalProductByUserId(userId);
        System.out.println(productList.get(0).getProdId());

        
        JsonArray jsonArr = ConversionUtil.finalProdtoJsonArr(productList);
        
        // ConversionUtil.replaceNull(payload);
        // System.out.println(">>>> payload: " + payload.toString());

        // String message = productSvc.createFinalProduct(payload);
        System.out.println(">>>>> the jsonarray: " + jsonArr.toString());
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonArr.toString()); 
    }

}
