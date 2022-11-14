package vttp.csf.server.models;

import java.math.BigDecimal;
import java.util.Date;

public class OrderResp {

    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String userId;
    private Date dateCreated;
    private String imageUrl;
    private int unitQuantity;
    private BigDecimal unitPrice;
    private String prodId;
    private String prodName;

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }
    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getUnitQuantity() {
        return unitQuantity;
    }
    public void setUnitQuantity(int unitQuantity) {
        this.unitQuantity = unitQuantity;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    
    @Override
    public String toString() {
        return "OrderResp [orderTrackingNumber=" + orderTrackingNumber + ", totalQuantity=" + totalQuantity
                + ", totalPrice=" + totalPrice + ", userId=" + userId + ", dateCreated=" + dateCreated + ", imageUrl="
                + imageUrl + ", unitQuantity=" + unitQuantity + ", unitPrice=" + unitPrice + ", prodId=" + prodId
                + ", prodName=" + prodName + "]";
    }

    

    
    
}
