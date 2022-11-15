package vttp.csf.server.models;

import java.math.BigDecimal;

public class Favourite {

    private String id;
    private String imgLink;
    private String prodName;
    private BigDecimal price;
    private String userId;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getImgLink() {
        return imgLink;
    }
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Favourite [id=" + id + ", imgLink=" + imgLink + ", prodName=" + prodName + ", price=" + price
                + ", userId=" + userId + "]";
    }

    
    
}
