package vttp.csf.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static vttp.csf.server.repository.Queries.*;

import java.util.LinkedList;
import java.util.List;

import vttp.csf.server.models.Favourite;
import vttp.csf.server.models.FinalProduct;

@Repository
public class ProductRepository {
    
    @Autowired
    private JdbcTemplate template;

    public boolean createFinalProduct(FinalProduct finalProduct) {
        int count = template.update(SQL_INSERT_FINAL_PRODUCT,
                finalProduct.getProdName(),
                finalProduct.getProdId(),
                finalProduct.getUserId(),
                finalProduct.getWithBoot(),
                finalProduct.getUpsize(),
                finalProduct.getHoopStraps(),
                finalProduct.getKeychainHolders(),
                finalProduct.getKeychainNum(),
                finalProduct.getExteriorDesign(),
                finalProduct.getBaseBagDesign(),
                finalProduct.getBootDesign(),
                finalProduct.getBaseType(),
                finalProduct.getFrontSideClosure(),
                finalProduct.getMagneticClosure(),
                finalProduct.getdRingWebbing(),
                finalProduct.getFrontPocketDesign(),
                finalProduct.getFrontPocketBackDesign(),
                finalProduct.getBackDesign(),
                finalProduct.getBaseBucketDesign(),
                finalProduct.getQuantity(),
                finalProduct.getRemarks(),
                finalProduct.getPrice(),
                finalProduct.getImgLink());

        return count == 1;
    }

    public List<FinalProduct> getFinalProduct(String userId) {
        List<FinalProduct> productList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_FINAL_PRODUCT_BY_USER_ID, userId);

        while (rs.next()) {
            FinalProduct product = new FinalProduct();
            product.setId(rs.getString("id"));
            product.setId(rs.getString("prod_name"));
            product.setProdId(rs.getString("prod_id"));
            product.setUserId(rs.getString("user_id"));
            product.setWithBoot(rs.getString("with_boot"));
            product.setUpsize(rs.getString("upsize"));
            product.setHoopStraps(rs.getString("hoop_waist_strap"));
            product.setKeychainHolders(rs.getString("keychain_holders"));
            product.setKeychainNum(rs.getInt("keychain_num"));
            product.setExteriorDesign(rs.getString("ext_design"));
            product.setBaseBagDesign(rs.getString("base_bag_design"));
            product.setBootDesign(rs.getString("boot_design"));
            product.setBaseType(rs.getString("base_type"));
            product.setFrontSideClosure(rs.getString("front_side_closure"));
            product.setMagneticClosure(rs.getString("magnetic_closure"));
            product.setdRingWebbing(rs.getString("d_ring_webbing"));
            product.setFrontPocketDesign(rs.getString("front_pocket_design"));
            product.setFrontPocketBackDesign(rs.getString("front_pocket_back_design"));
            product.setBackDesign(rs.getString("back_design"));
            product.setBaseBucketDesign(rs.getString("base_bucket_design"));
            product.setQuantity(rs.getInt("quantity"));
            product.setRemarks(rs.getString("remarks"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setImgLink(rs.getString("imgLink"));
            
            productList.add(product);
        }
        return productList;
    }


    public boolean addFav(Favourite fav) {
        int count = template.update(SQL_ADD_FAVOURITE,
                fav.getImgLink(),
                fav.getProdName(),
                fav.getPrice(),
                fav.getUserId());

        return count == 1;
    }

    public List<Favourite> getFav(String userId) {
        List<Favourite> favList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_FAV_BY_USER_ID, userId);

        while (rs.next()) {
            Favourite fav = new Favourite();
            fav.setId(rs.getString("id"));
            fav.setImgLink(rs.getString("img_link"));
            fav.setProdName(rs.getString("prod_name"));
            fav.setPrice(rs.getBigDecimal("prod_price"));
            fav.setUserId(rs.getString("user_id"));
            
            favList.add(fav);
        }
        return favList;
    }

    public boolean deleteFav(String id) {

        int count = template.update(SQL_DELETE_FAVOURITE, id);
        return count == 1;
    }



}
