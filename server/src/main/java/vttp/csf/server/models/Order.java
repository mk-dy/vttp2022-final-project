package vttp.csf.server.models;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

    private int id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    private Date dateCreated;
    // private Date lastUpdated;
    private Set<OrderItem> orderItems = new HashSet<>();
    private User user;
    private Address shippingAddress;
    private Address billingAddress;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Address getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public Address getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }
    @Override
    public String toString() {
        return "Order [id=" + id + ", orderTrackingNumber=" + orderTrackingNumber + ", totalQuantity=" + totalQuantity
                + ", totalPrice=" + totalPrice + ", status=" + status + ", dateCreated=" + dateCreated + ", orderItems="
                + orderItems + ", user=" + user + ", shippingAddress=" + shippingAddress + ", billingAddress="
                + billingAddress + "]";
    }

    
    
    
}









