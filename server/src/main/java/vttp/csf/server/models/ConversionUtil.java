package vttp.csf.server.models;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class ConversionUtil {
    
    public static JsonArray toJsonArr(List<Product> prodList) {
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Product product: prodList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",product.getId())
                    .add("name",product.getName())
                    .add("descr", product.getDescr())
                    .add("price", product.getPrice())
                    .add("imgLink", product.getImgLink())
                    .build();

            arrBuilder.add(jsonObj);
        }
        JsonArray arr = arrBuilder.build();

        return arr;
    }
    
    public static JsonArray fabListToJsonArr(List<Fabric> fabricList) {
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Fabric fabric: fabricList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",fabric.getId())
                    .add("name",fabric.getName())
                    .add("imgLink", fabric.getImgLink())
                    .build();

            arrBuilder.add(jsonObj);
        }
        JsonArray arr = arrBuilder.build();

        return arr;
    }

    public static JsonObject toJson(Product product) {
        JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",product.getId())
                    .add("name",product.getName())
                    .add("descr", product.getDescr())
                    .add("price", product.getPrice())
                    .add("imgLink", product.getImgLink())
                    .build();
        return jsonObj;
    }

}
