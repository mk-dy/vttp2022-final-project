package vttp.csf.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp.csf.server.repository.Queries.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

import vttp.csf.server.models.Address;
import vttp.csf.server.models.Order;
import vttp.csf.server.models.OrderItem;

@Repository
public class CheckoutRepository {

    @Value("${spring.datasource.url}") 
    String sqlUrl;

    @Value("${spring.datasource.username}") 
    String sqlUser;

    @Value("${spring.datasource.password}") 
    String sqlPassword;

    
    @Autowired
    private JdbcTemplate template;

    // insert address into address table
    // get address id
    public boolean addAddress(Address address) {
        int count = template.update(SQL_INSERT_ADDRESS,
                address.getStreet(),
                address.getPostalCode());

        return count == 1;
    }

    public Integer getAddressId() {
        SqlRowSet result = template.queryForRowSet(SQL_GET_ADDRESS_ID);

        result.next();
        Integer addressId = result.getInt("id");
        System.out.println(">>>> Address ID Check: " + addressId);
        
        System.out.println(">>>> spring Check: " + sqlUrl);
        System.out.println(">>>> spring Check: " + sqlUser);
        System.out.println(">>>> spring Check: " + sqlPassword);

        return addressId;
        
    }

    public boolean addOrder(Order order) {
        int count = template.update(SQL_INSERT_ORDER,
                order.getOrderTrackingNumber(),
                order.getTotalQuantity(),
                order.getTotalPrice(),
                order.getDateCreated(),
                order.getUser().getId(),
                order.getShippingAddress().getId());

        return count == 1;
    }

    public Integer getOrderId() {
        SqlRowSet result = template.queryForRowSet(SQL_GET_ORDER_ID);

        result.next();
        Integer orderId = result.getInt("id");
        System.out.println(">>>> Address ID Check: " + orderId);
        return orderId;
        
    }

    // public void addOrderItem(Set<OrderItem> orderItemSet) {
        
    //     try (Connection conn = DriverManager.getConnection(
    //             sqlUrl, sqlUser, sqlPassword);
             
    //          PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT_ORDER_ITEM)) {

    //         // commit all or rollback all, if any errors
    //         conn.setAutoCommit(false); // default true

    //         // Run list of insert commands
    //         for (OrderItem item: orderItemSet) {
    //             psInsert.setString(1, item.getImageUrl());
    //             psInsert.setInt(2, item.getQuantity());
    //             psInsert.setBigDecimal(3, item.getUnitPrice());
    //             psInsert.setString(4, item.getProdId());
    //             psInsert.setInt(5, item.getOrder().getId());

    //             psInsert.addBatch();
    //         }
    //         int[] rows = psInsert.executeBatch();

    //         System.out.println("Updated successfully" + Arrays.toString(rows));

    //         conn.commit();
            

    //     } catch (SQLException e) {
    //         System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public boolean insertOrderItem(OrderItem item) {
        int count = template.update(SQL_INSERT_ORDER_ITEM,
                                    item.getImageUrl(),
                                    item.getQuantity(),
                                    item.getUnitPrice(),
                                    item.getProdId(),
                                    item.getProdName(),
                                    item.getOrder().getId());
        return 1 == count;
    }


    public boolean insertOrderItems(Set<OrderItem> orderItems) {
        for (OrderItem item: orderItems)
            if (!insertOrderItem(item))
                return false;
        return true;
    }



}
