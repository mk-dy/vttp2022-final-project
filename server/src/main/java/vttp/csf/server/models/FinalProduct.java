package vttp.csf.server.models;

import java.math.BigDecimal;
// >>>> payload: {"baseType":"half","frontOrSideClosure":"front","magneticClosure":"yes","dRingWebbing":"yes","frontPocketDesign":"Olive","frontPocketBackDesign":"Black","backDesign":"Flora","quantity":2,"remarks":"ok","baseBucketDesign":"","prod_id":"CHLKBKT02","price":148}
       
public class FinalProduct {
    private String id;
    private String prodId;
    private String userId;

    // chalk bag
    private String baseBagDesign;
    private String bootDesign;
    private String exteriorDesign;
    private String withBoot;
    private String hoopStraps;
    private String keychainHolders;
    private int keychainNum;
    private String upsize;

    // chalk bucket
    private String baseType;
    private String frontSideClosure;
    private String magneticClosure;
    private String dRingWebbing;
    private String frontPocketDesign;
    private String frontPocketBackDesign;
    private String backDesign;
    private String baseBucketDesign;

    private int quantity;
    private String remarks;
    private BigDecimal price;
    private String imgLink;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBaseBagDesign() {
        return baseBagDesign;
    }
    public void setBaseBagDesign(String baseBagDesign) {
        this.baseBagDesign = baseBagDesign;
    }
    public String getBootDesign() {
        return bootDesign;
    }
    public void setBootDesign(String bootDesign) {
        this.bootDesign = bootDesign;
    }
    public String getExteriorDesign() {
        return exteriorDesign;
    }
    public void setExteriorDesign(String exteriorDesign) {
        this.exteriorDesign = exteriorDesign;
    }
    public String getWithBoot() {
        return withBoot;
    }
    public void setWithBoot(String withBoot) {
        this.withBoot = withBoot;
    }
    public String getHoopStraps() {
        return hoopStraps;
    }
    public void setHoopStraps(String hoopStraps) {
        this.hoopStraps = hoopStraps;
    }
    public String getKeychainHolders() {
        return keychainHolders;
    }
    public void setKeychainHolders(String keychainHolders) {
        this.keychainHolders = keychainHolders;
    }
    public int getKeychainNum() {
        return keychainNum;
    }
    public void setKeychainNum(int keychainNum) {
        this.keychainNum = keychainNum;
    }
    public String getUpsize() {
        return upsize;
    }
    public void setUpsize(String upsize) {
        this.upsize = upsize;
    }
    public String getBaseType() {
        return baseType;
    }
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    public String getFrontSideClosure() {
        return frontSideClosure;
    }
    public void setFrontSideClosure(String frontSideClosure) {
        this.frontSideClosure = frontSideClosure;
    }
    public String getMagneticClosure() {
        return magneticClosure;
    }
    public void setMagneticClosure(String magneticClosure) {
        this.magneticClosure = magneticClosure;
    }
    public String getdRingWebbing() {
        return dRingWebbing;
    }
    public void setdRingWebbing(String dRingWebbing) {
        this.dRingWebbing = dRingWebbing;
    }
    public String getFrontPocketDesign() {
        return frontPocketDesign;
    }
    public void setFrontPocketDesign(String frontPocketDesign) {
        this.frontPocketDesign = frontPocketDesign;
    }
    public String getFrontPocketBackDesign() {
        return frontPocketBackDesign;
    }
    public void setFrontPocketBackDesign(String frontPocketBackDesign) {
        this.frontPocketBackDesign = frontPocketBackDesign;
    }
    public String getBackDesign() {
        return backDesign;
    }
    public void setBackDesign(String backDesign) {
        this.backDesign = backDesign;
    }
    public String getBaseBucketDesign() {
        return baseBucketDesign;
    }
    public void setBaseBucketDesign(String baseBucketDesign) {
        this.baseBucketDesign = baseBucketDesign;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return "FinalProduct [id=" + id + ", prodId=" + prodId + ", userId=" + userId + ", baseBagDesign="
                + baseBagDesign + ", bootDesign=" + bootDesign + ", exteriorDesign=" + exteriorDesign + ", withBoot="
                + withBoot + ", hoopStraps=" + hoopStraps + ", keychainHolders=" + keychainHolders + ", keychainNum="
                + keychainNum + ", upsize=" + upsize + ", baseType=" + baseType + ", frontSideClosure="
                + frontSideClosure + ", magneticClosure=" + magneticClosure + ", dRingWebbing=" + dRingWebbing
                + ", frontPocketDesign=" + frontPocketDesign + ", frontPocketBackDesign=" + frontPocketBackDesign
                + ", backDesign=" + backDesign + ", baseBucketDesign=" + baseBucketDesign + ", quantity=" + quantity
                + ", remarks=" + remarks + ", price=" + price + ", imgLink=" + imgLink + "]";
    }

}
