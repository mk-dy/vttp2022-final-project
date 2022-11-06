package vttp.csf.server.repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static vttp.csf.server.repository.Queries.*;

import vttp.csf.server.models.Fabric;
import vttp.csf.server.models.Product;

@Repository
public class SearchRepository {
    
    @Autowired
    private JdbcTemplate template;

    public List<Product> getProduct() {
        List<Product> productList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_PRODUCT);

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getString("prod_id"));
            product.setAccess(rs.getString("prod_access"));
            product.setName(rs.getString("prod_name"));
            product.setDescr(rs.getString("prod_desc"));
            product.setPrice(rs.getBigDecimal("prod_price"));
            product.setImgLink(rs.getString("prod_img_link"));
            
            productList.add(product);
        }
        return productList;
    }

    // for search
    public Optional<List<Product>> searchProduct(String query) {
        List<Product> productList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_PRODUCT_BY_QUERY, query);

        if (!rs.next()) {
            return Optional.empty();
        }

        rs.beforeFirst();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getString("prod_id"));
            product.setAccess(rs.getString("prod_access"));
            product.setName(rs.getString("prod_name"));
            product.setDescr(rs.getString("prod_desc"));
            product.setPrice(rs.getBigDecimal("prod_price"));
            product.setImgLink(rs.getString("prod_img_link"));
            
            productList.add(product);
        }
        return Optional.of(productList);
    }

    public List<Fabric> getFabric() {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_FABRIC);
        List<Fabric> fabricList = new LinkedList<>();

        while (rs.next()) {
            Fabric fabric = new Fabric();
            fabric.setId(rs.getString("id"));
            fabric.setName(rs.getString("name"));
            fabric.setImgLink(rs.getString("img_link"));
            fabricList.add(fabric);
        }
        return fabricList;
    }

    public Product getChalkbag() {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_CHALKBAG);

        Product chalkBag = new Product();
        rs.next();
        chalkBag.setId(rs.getString("prod_id"));
        chalkBag.setAccess(rs.getString("prod_access"));
        chalkBag.setName(rs.getString("prod_name"));
        chalkBag.setDescr(rs.getString("prod_desc"));
        chalkBag.setPrice(rs.getBigDecimal("prod_price"));
        chalkBag.setImgLink(rs.getString("prod_img_link"));
        
        return chalkBag;
    }

    public Product getChalkbucket() {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_CHALKBUCKET);

        Product chalkBucket = new Product();
        rs.next();
        chalkBucket.setId(rs.getString("prod_id"));
        chalkBucket.setAccess(rs.getString("prod_access"));
        chalkBucket.setName(rs.getString("prod_name"));
        chalkBucket.setDescr(rs.getString("prod_desc"));
        chalkBucket.setPrice(rs.getBigDecimal("prod_price"));
        chalkBucket.setImgLink(rs.getString("prod_img_link"));
        
        return chalkBucket;
    }
}
