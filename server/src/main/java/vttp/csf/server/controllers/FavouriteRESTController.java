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
import vttp.csf.server.models.Favourite;
import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.repository.SearchRepository;
import vttp.csf.server.services.ProductService;
import vttp.csf.server.utility.ConversionUtil;

@RestController
public class FavouriteRESTController {
    
    @Autowired
    private ProductService productSvc;

    @PostMapping(path="/favourites")
    public ResponseEntity<String> addToFav(@RequestBody Favourite fav) {
        
        System.out.println(">>>> payload: " + fav.toString());

        // to create final product and add to final_product table
        String message = productSvc.addFav(fav);
        System.out.println(">>>>> message: " + message);
        
        if (message.contains("Success")) {
            JsonObject jsonObj = Json.createObjectBuilder().add("message","Added").build();
            return ResponseEntity.status(HttpStatus.OK).body(jsonObj.toString());
        } else {
            JsonObject jsonObj = Json.createObjectBuilder().add("message","Not Added").build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObj.toString());
        }     
    }

    @PostMapping(path="/delete-favourite")
    public ResponseEntity<String> removeFav(@RequestBody String id) {
        
        System.out.println(">>>> payload: " + id);

        // to create final product and add to final_product table
        String message = productSvc.deleteFav(id);
        System.out.println(">>>>> message: " + message);
        
        if (message.contains("Success")) {
            JsonObject jsonObj = Json.createObjectBuilder().add("message",message).build();
            return ResponseEntity.status(HttpStatus.OK).body(jsonObj.toString());
        } else {
            JsonObject jsonObj = Json.createObjectBuilder().add("message",message).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObj.toString());
        }     
    }


    @GetMapping(path="/favourites/{userId}")
    public ResponseEntity<String> showFav(@PathVariable String userId) {
        
        List<Favourite> favList = new LinkedList<>();
        favList = this.productSvc.getFav(userId);
        System.out.println(favList.get(0).getId());
        
        JsonArray jsonArr = ConversionUtil.favListToJsonArr(favList);
        System.out.println(">>>>> the jsonarray: " + jsonArr.toString());
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonArr.toString()); 
    }

}
