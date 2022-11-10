package vttp.csf.server.models;

import java.util.Set;

public class Purchase {

    private User user;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<FinalProduct> orderItems;
    
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
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Set<FinalProduct> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<FinalProduct> orderItems) {
        this.orderItems = orderItems;
    }

    

}
