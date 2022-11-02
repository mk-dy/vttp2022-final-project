package vttp.csf.server.controllers;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.csf.server.models.ConversionUtil;
import vttp.csf.server.models.Fabric;
import vttp.csf.server.models.Product;
import vttp.csf.server.services.SearchService;

@RestController
public class ProductRESTController {
    
    @Autowired
    private SearchService searchSvc;

    @GetMapping(path="/shop")
    public ResponseEntity<String> getProduct() {

        List<Product> productList = searchSvc.getProduct();

        JsonArray arr = ConversionUtil.toJsonArr(productList);
        
        // i get all my product details from product table
        // return to front end service
        // push out all the data into main page component
        return ResponseEntity.status(HttpStatus.OK).body(arr.toString());
    }

    @GetMapping(path="/product/chalkbag")
    public ResponseEntity<String> getChalkbag() {

        Product chalkbag = new Product();
        chalkbag = searchSvc.getChalkbag();
        JsonObject obj = ConversionUtil.toJson(chalkbag);

        // actually can put fabric data here as well
        // List<Fabric> fabricList = new LinkedList<>();
        // fabricList = searchSvc.getFabric();
        // JsonArray fabJsonArr = ConversionUtil.fabListToJsonArr(fabricList);

        // JsonObject jObj = Json.createObjectBuilder().add("chalkbag",obj).add("fabric",fabJsonArr).build();
        // System.out.println(">>>>>>> check the jsonArray data:" + jObj.toString());
        
        return ResponseEntity.status(HttpStatus.OK).body(obj.toString());
        // return ResponseEntity.status(HttpStatus.OK).body(jObj.toString());
        
    }

    @GetMapping(path="/fabric")
    public ResponseEntity<String> getFabric() {
        List<Fabric> fabricList = new LinkedList<>();
        fabricList = searchSvc.getFabric();
        JsonArray fabJsonArr = ConversionUtil.fabListToJsonArr(fabricList);

        // System.out.println(">>>>>>> check the jsonArray data:" + fabJsonArr.toString());
        return ResponseEntity.status(HttpStatus.OK).body(fabJsonArr.toString()); 
    }
    
    // @GetMapping(path="/product/{productId}")
    // public ResponseEntity<String> getProduct() {
    //     when click on path product/productId

        
    // }
}
