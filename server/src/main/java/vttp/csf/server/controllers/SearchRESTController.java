package vttp.csf.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.csf.server.models.ConversionUtil;
import vttp.csf.server.models.Product;
import vttp.csf.server.services.SearchService;

@RestController
public class SearchRESTController {
    
    @Autowired
    private SearchService searchSvc;

    @GetMapping(path="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchProduct(@RequestParam String query) {

        Optional<List<Product>> optList = searchSvc.searchProduct(query);
        if (optList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        List<Product> productList = optList.get();
        
        System.out.println(">>>>>> check productList: " + productList.get(0).getName());

        // JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
                
        // for (Product product: productList) {
        //     JsonObject jsonObj = Json.createObjectBuilder()
        //             .add("id",product.getId())
        //             .add("name",product.getName())
        //             .add("descr", product.getDescr())
        //             .add("price", product.getPrice())
        //             .add("imgLink", product.getImgLink())
        //             .build();

        //     arrBuilder.add(jsonObj);
        // }
        // JsonArray arr = arrBuilder.build();
        JsonArray arr = ConversionUtil.toJsonArr(productList);
        
        return ResponseEntity.status(HttpStatus.OK).body(arr.toString());
    }
}
