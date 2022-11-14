package vttp.csf.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.csf.server.models.OrderResp;
import vttp.csf.server.services.OrderService;
import vttp.csf.server.utility.ConversionUtil;

@RestController
public class OrderRESTController {
    
    @Autowired
    private OrderService orderSvc;

    @GetMapping(path="/my-orders/{userId}")
    public ResponseEntity<String> getUser(@PathVariable String userId) {
        System.out.println(">>> userId: " + userId);
        
        // get orders by userId
        Optional<List<OrderResp>> orderListOpt = orderSvc.getOrderById(userId);
        
        if (orderListOpt.isEmpty()) {
            JsonObject jsonObj = Json.createObjectBuilder().add("message","You have no orders currently.").build();
        }

        List<OrderResp> orderList = orderListOpt.get();
        JsonArray array = ConversionUtil.orderToJson(orderList);
        
         return ResponseEntity.status(HttpStatus.OK).body(array.toString());
    }
}
