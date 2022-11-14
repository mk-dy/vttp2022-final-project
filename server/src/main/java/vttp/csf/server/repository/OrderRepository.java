package vttp.csf.server.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp.csf.server.repository.Queries.*;

import vttp.csf.server.models.OrderResp;

@Repository
public class OrderRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<List<OrderResp>> getByUserId(String userId) {
        SqlRowSet result = template.queryForRowSet(SQL_GET_ORDER_BY_USERID, userId);

        if (!result.next()) {
            return Optional.empty();

        } else {
            List<OrderResp> orderList = new LinkedList<>();
            result.beforeFirst();
            while(result.next()) {
                OrderResp order = new OrderResp();
                order.setOrderTrackingNumber(result.getString("order_tracking_number"));
                order.setTotalQuantity(result.getInt("total_quantity"));
                order.setTotalPrice(result.getBigDecimal("total_price"));
                order.setUserId(result.getString("user_id"));
                order.setDateCreated(result.getDate("date_created"));
                order.setImageUrl(result.getString("img_Link"));
                order.setUnitQuantity(result.getInt("quantity"));
                order.setUnitPrice(result.getBigDecimal("unit_price"));
                order.setProdId(result.getString("prod_id"));
                order.setProdName(result.getString("prod_name"));

                orderList.add(order);
            }

            return Optional.of(orderList);
        }
    }
}
