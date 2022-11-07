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
                    .add("access",product.getAccess())
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
                    .add("access",product.getAccess())
                    .add("name",product.getName())
                    .add("descr", product.getDescr())
                    .add("price", product.getPrice())
                    .add("imgLink", product.getImgLink())
                    .build();
        return jsonObj;
    }

    // public static FinalProduct replaceNull(FinalProduct product) {
    //     // if product properties contain null, replace with ""
    //     if (product.getUserId().equals(null)) {
    //         product.setUserId("");
    //     }
    //     if (product.getExteriorDesign().equals(null)) {
    //         product.setExteriorDesign("");
    //     }
    //     if (product.getWithBoot().equals(null)) {
    //         product.setWithBoot("");
    //     }
    //     if (product.getHoopStraps().equals(null)) {
    //         product.setHoopStraps("");
    //     }
    //     if (product.getKeychainHolders().equals(null)) {
    //         product.setKeychainHolders("");
    //     }
    //     if (product.getUpsize().equals(null)) {
    //         product.setUpsize("");
    //     }
    //     if (product.getBaseType().equals(null)) {
    //         product.setBaseType("");
    //     }
    //     if (product.getFrontSideClosure().equals(null)) {
    //         product.setFrontSideClosure("");
    //     }
    //     if (product.getMagneticClosure().equals(null)) {
    //         product.setMagneticClosure("");
    //     }
    //     if (product.getdRingWebbing().equals(null)) {
    //         product.setdRingWebbing("");
    //     }
    //     if (product.getFrontPocketDesign().equals(null)) {
    //         product.setFrontPocketDesign("");
    //     }
    //     if (product.getFrontPocketBackDesign().equals(null)) {
    //         product.setFrontPocketBackDesign("");
    //     }
    //     if (product.getBackDesign().equals(null)) {
    //         product.setBackDesign("");
    //     }
    //     if (product.getBaseBucketDesign().equals(null)) {
    //         product.setBaseBucketDesign("");
    //     }
    //     return product;
        
    // }

    public static FinalProduct replaceNull(FinalProduct product) {
        // if product properties contain null, replace with ""
        if (product.getUserId() == null) {
            product.setUserId("");
        }
        if (product.getExteriorDesign()== null) {
            product.setExteriorDesign("");
        }
        if (product.getBaseBagDesign()== null) {
            product.setBaseBagDesign("");
        }
        if (product.getBootDesign()== null) {
            product.setBootDesign("");
        }
        if (product.getWithBoot()== null) {
            product.setWithBoot("");
        }
        if (product.getHoopStraps()== null) {
            product.setHoopStraps("");
        }
        if (product.getKeychainHolders()== null) {
            product.setKeychainHolders("");
        }
        if (product.getUpsize()== null) {
            product.setUpsize("");
        }
        if (product.getBaseType()== null) {
            product.setBaseType("");
        }
        if (product.getFrontSideClosure()== null) {
            product.setFrontSideClosure("");
        }
        if (product.getMagneticClosure()== null) {
            product.setMagneticClosure("");
        }
        if (product.getdRingWebbing()== null) {
            product.setdRingWebbing("");
        }
        if (product.getFrontPocketDesign()== null) {
            product.setFrontPocketDesign("");
        }
        if (product.getFrontPocketBackDesign()== null) {
            product.setFrontPocketBackDesign("");
        }
        if (product.getBackDesign()== null) {
            product.setBackDesign("");
        }
        if (product.getBaseBucketDesign()== null) {
            product.setBaseBucketDesign("");
        }
        return product;
        
    }

}
