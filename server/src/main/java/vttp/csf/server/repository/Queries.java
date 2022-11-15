package vttp.csf.server.repository;

public interface Queries {
    
    // get product
    public static final String SQL_SELECT_ALL_PRODUCT = 
    "SELECT * FROM product";

    // get only chalkbag
    public static final String SQL_SELECT_CHALKBAG =
    "SELECT * FROM product WHERE prod_id = 'CHLKBAG01'";

    // get only chalkbucket
    public static final String SQL_SELECT_CHALKBUCKET =
    "SELECT * FROM product WHERE prod_id = 'CHLKBKT02'";

    // insert final prod
    public static final String SQL_INSERT_FINAL_PRODUCT = 
    "INSERT INTO final_product (prod_name, prod_id, user_id, with_boot, upsize, hoop_waist_strap, keychain_holders, keychain_num, ext_design, base_bag_design, boot_design, base_type, front_side_closure, magnetic_closure, d_ring_webbing, front_pocket_design, front_pocket_back_design, back_design, base_bucket_design, quantity, remarks, price, imgLink ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    
    // get all final product by user_id
    public static final String SQL_GET_FINAL_PRODUCT_BY_USER_ID = 
    "SELECT * FROM final_product WHERE user_id = ?";

    // search-related
    public static final String SQL_SELECT_ALL_PRODUCT_BY_QUERY = 
    "SELECT * FROM product WHERE prod_name LIKE CONCAT('%',?,'%')";
    
    // get product fabric
    public static final String SQL_SELECT_ALL_FABRIC =
    "SELECT * FROM fabric";

    // USER
    public static final String SQL_GET_USER_BY_EMAIL = 
    "SELECT * FROM user WHERE user_email = ?"; 

    public static final String SQL_INSERT_USER = 
    "INSERT INTO user (user_id, user_first_name, user_last_name, user_email, user_mobile, user_password) VALUES (?,?,?,?,?,?)"; 
    
    // ADDRESS
    public static final String SQL_INSERT_ADDRESS = 
    "INSERT INTO address (street, postal_code) VALUES (?,?)"; 

    public static final String SQL_GET_ADDRESS_ID = 
    "SELECT id FROM address ORDER BY id DESC LIMIT 1";

    // ORDER
    public static final String SQL_INSERT_ORDER = 
    "INSERT INTO user_orders (order_tracking_number, total_quantity, total_price, date_created, user_id, shipping_address_id) VALUES (?,?,?,?,?,?)"; 

    public static final String SQL_GET_ORDER_ID = 
    "SELECT id FROM user_orders ORDER BY id DESC LIMIT 1";

    public static final String SQL_INSERT_ORDER_ITEM = 
    "INSERT INTO order_item (img_link, quantity, unit_price, prod_id, prod_name, order_id) VALUES (?,?,?,?,?,?)"; 

    public static final String SQL_GET_ORDER_BY_USERID = 
    "SELECT * FROM (SELECT user_orders.order_tracking_number, user_orders.total_quantity, user_orders.total_price, user_orders.user_id, user_orders.date_created, order_item.img_Link, order_item.quantity, order_item.unit_price, order_item.prod_id, order_item.prod_name FROM user_orders INNER JOIN order_item ON user_orders.id = order_item.order_id) AS sub WHERE user_id = ?";

    public static final String SQL_GET_ORDER = 
    "SELECT id, order_tracking_number, total_quantity, total_price, date_created FROM user_orders WHERE user_id = ?";

    public static final String SQL_GET_ORDER_ITEM = 
    "SELECT id, img_link, quantity, unit_price, prod_id, prod_name FROM order_item WHERE order_id = ?";



    // FAVOURITE 
    public static final String SQL_ADD_FAVOURITE = 
    "INSERT INTO favourites (img_link, prod_name, prod_price, user_id) VALUES (?,?,?,?)"; 

    public static final String SQL_GET_FAV_BY_USER_ID = 
    "SELECT * FROM favourites WHERE user_id = ?";

    public static final String SQL_DELETE_FAVOURITE = 
    "DELETE FROM favourites WHERE id = ?";
}