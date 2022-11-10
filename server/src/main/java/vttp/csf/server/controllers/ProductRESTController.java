package vttp.csf.server.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.csf.server.models.Fabric;
import vttp.csf.server.models.Product;
import vttp.csf.server.services.SearchService;
import vttp.csf.server.utility.ConversionUtil;

@RestController
public class ProductRESTController {
    
    @Autowired
    private AmazonS3 s3;

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

    @GetMapping(path="/product/chalkbucket")
    public ResponseEntity<String> getChalkbucket() {

        Product chalkbucket = new Product();
        chalkbucket = searchSvc.getChalkbucket();
        JsonObject obj = ConversionUtil.toJson(chalkbucket);

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

        return ResponseEntity.status(HttpStatus.OK).body(fabJsonArr.toString()); 
        // return ResponseEntity.status(HttpStatus.OK).body("it is ok");
    }

    @GetMapping(path="/fabricTest")
    public ResponseEntity<List<byte[]>> getFabricTest() {
        List<Fabric> fabricList = new LinkedList<>();
        fabricList = searchSvc.getFabric();
        JsonArray fabJsonArr = ConversionUtil.fabListToJsonArr(fabricList);

        // System.out.println(">>>>>>> check the jsonArray data:" + fabJsonArr.toString());


        // list of s3 objects
        List<String> myFiles = getFilesFromSpaces();
        System.out.println(myFiles);

        
        List<byte[]> byteArrList = new ArrayList<>();

        // get object request
        for (String fileName: myFiles) {

            GetObjectRequest getReq = new GetObjectRequest(
                "mattstorage", "vttp-final-project/Fabrics/%s".formatted(fileName));
    
            S3Object s3Obj = null;
    
            try {
                System.out.println(">>>>>> check image: " + fileName);
                s3Obj = s3.getObject(getReq);
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
    
            byte[] buffer = new byte[0];
    
            try {
                buffer = IOUtils.toByteArray(s3Obj.getObjectContent());
            } catch (IOException ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
            byteArrList.add(buffer);

        }

        // return ResponseEntity.status(HttpStatus.OK).body(fabJsonArr.toString()); 
        return ResponseEntity.status(HttpStatus.OK).body(byteArrList);
    }
    
    // @GetMapping(path="/product/{productId}")
    // public ResponseEntity<String> getProduct() {
    //     when click on path product/productId

        
    // }

    public List<String> getFilesFromSpaces() {
        ListObjectsV2Result result = s3.listObjectsV2("mattstorage", "vttp-final-project/Fabrics");
        List<S3ObjectSummary> objects = result.getObjectSummaries();
    
        List<String> fileList = objects.stream()
            .map(S3ObjectSummary -> {
                return S3ObjectSummary.getKey();
            }).collect(Collectors.toList());

        fileList.remove(0);
        fileList.replaceAll(s -> s.replace("vttp-final-project/Fabrics/",""));
        
        return fileList;
    }




    
}