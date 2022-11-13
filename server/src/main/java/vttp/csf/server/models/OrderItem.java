package vttp.csf.server.models;

import java.math.BigDecimal;

public class OrderItem {

    private int id;
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;
    private String prodId;
    private Order order;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", imageUrl=" + imageUrl + ", unitPrice=" + unitPrice + ", quantity=" + quantity
                + ", prodId=" + prodId + ", order=" + order + "]";
    }

    

    
}








