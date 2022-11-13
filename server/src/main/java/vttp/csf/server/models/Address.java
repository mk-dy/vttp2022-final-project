package vttp.csf.server.models;

public class Address {

    private Integer id;
    private String street;
    private String postalCode;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", postalCode=" + postalCode + "]";
    }
    

}





