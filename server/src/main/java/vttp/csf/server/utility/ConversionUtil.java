package vttp.csf.server.utility;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.csf.server.models.Fabric;
import vttp.csf.server.models.Favourite;
import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.models.Order;
import vttp.csf.server.models.OrderItem;
// import vttp.csf.server.models.OrderResp;
import vttp.csf.server.models.Product;
import vttp.csf.server.models.User;

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
    
    public static JsonArray finalProdtoJsonArr(List<FinalProduct> prodList) {
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (FinalProduct product: prodList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",product.getId())
                    .add("prodName",product.getProdName())
                    .add("prodId",product.getProdId())
                    .add("userId",product.getUserId())
                    .add("baseBagDesign",product.getBaseBagDesign())
                    .add("bootDesign",product.getBootDesign())
                    .add("exteriorDesign",product.getExteriorDesign())
                    .add("withBoot",product.getWithBoot())
                    .add("hoopStraps",product.getHoopStraps())
                    .add("keychainHolders",product.getKeychainHolders())
                    .add("baseType",product.getBaseType())
                    .add("frontSideClosure",product.getFrontSideClosure())
                    .add("magneticClosure",product.getMagneticClosure())
                    .add("dRingWebbing",product.getdRingWebbing())
                    .add("frontPocketDesign",product.getFrontPocketDesign())
                    .add("frontPocketBackDesign",product.getFrontPocketBackDesign())
                    .add("backDesign",product.getBackDesign())
                    .add("baseBucketDesign",product.getBaseBucketDesign())
                    .add("quantity",product.getQuantity())
                    .add("remarks",product.getRemarks())
                    .add("price",product.getPrice())
                    .add("imgLink",product.getImgLink())
                    .build();




            arrBuilder.add(jsonObj);
        }
        JsonArray arr = arrBuilder.build();

        return arr;
    }

    public static JsonArray favListToJsonArr(List<Favourite> favList) {
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Favourite fav: favList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",fav.getId())
                    .add("imgLink",fav.getImgLink())
                    .add("prodName",fav.getProdName())
                    .add("price", fav.getPrice())
                    .add("userId", fav.getUserId())
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

    public static JsonObject userToJson(User user) {
        JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",user.getId())
                    .add("firstName",user.getFirstName())
                    .add("lastName",user.getLastName())
                    .add("email", user.getEmail())
                    .add("mobile", user.getMobile())
                    .build();
        return jsonObj;
    }

    // public static JsonArray orderToJson(List<OrderResp> orderList) {

    //     JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

    //     for (OrderResp order: orderList) {
    //         JsonObject jsonObj = Json.createObjectBuilder()
    //                 .add("orderTrackingNumber",order.getOrderTrackingNumber())
    //                 .add("totalQuantity",order.getTotalQuantity())
    //                 .add("totalPrice", order.getTotalPrice())
    //                 .add("userId", order.getUserId())
    //                 .add("dateCreated", order.getDateCreated().toString())
    //                 .add("imageUrl", order.getImageUrl())
    //                 .add("unitQuantity", order.getUnitQuantity())
    //                 .add("unitPrice", order.getUnitPrice())
    //                 .add("prodId", order.getProdId())
    //                 .add("prodName", order.getProdName())
    //                 .build();

    //         arrBuilder.add(jsonObj);
    //     }
    //     JsonArray arr = arrBuilder.build();

    //     return arr;
    // }

    public static JsonArray ordToJson(List<Order> orderList) {

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Order order: orderList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",order.getId())
                    .add("orderTrackingNumber",order.getOrderTrackingNumber())
                    .add("totalQuantity",order.getTotalQuantity())
                    .add("totalPrice", order.getTotalPrice())
                    .add("dateCreated", order.getDateCreated().toString())
                    .build();

            arrBuilder.add(jsonObj);
        }
        JsonArray arr = arrBuilder.build();

        return arr;
    }

    public static JsonArray orderItemToJson(List<OrderItem> orderList) {

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (OrderItem order: orderList) {
            JsonObject jsonObj = Json.createObjectBuilder()
                    .add("id",order.getId())
                    .add("imageUrl",order.getImageUrl())
                    .add("quantity",order.getQuantity())
                    .add("unitPrice", order.getUnitPrice())
                    .add("prodId", order.getProdId())
                    .add("prodName", order.getProdName())
                    .build();

            arrBuilder.add(jsonObj);
        }
        JsonArray arr = arrBuilder.build();

        return arr;
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
        if (product.getRemarks()== null) {
            product.setRemarks("");
        }
        return product;
        
    }

}
