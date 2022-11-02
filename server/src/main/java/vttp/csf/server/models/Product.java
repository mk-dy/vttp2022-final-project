package vttp.csf.server.models;

import java.math.BigDecimal;


public class Product {
    
    private String id;
    private String name;
    private String descr;
    private BigDecimal price;
    private String imgLink;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getImgLink() {
        return imgLink;
    }
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    

}
