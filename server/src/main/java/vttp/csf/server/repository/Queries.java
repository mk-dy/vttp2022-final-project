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
    "INSERT INTO final_product (prod_id, user_id, with_boot, upsize, hoop_waist_strap, keychain_holders, keychain_num, ext_design, base_bag_design, boot_design, base_type, front_side_closure, magnetic_closure, d_ring_webbing, front_pocket_design, front_pocket_back_design, back_design, base_bucket_design, quantity, remarks, price, imgLink ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    
    // get all final product by user_id
    public static final String SQL_GET_FINAL_PRODUCT_BY_USER_ID = 
    "SELECT * FROM final_product WHERE user_id = ?";

    // search-related
    public static final String SQL_SELECT_ALL_PRODUCT_BY_QUERY = 
    "SELECT * FROM product WHERE prod_name LIKE CONCAT('%',?,'%')";
    
    // get product fabric
    public static final String SQL_SELECT_ALL_FABRIC =
    "SELECT * FROM fabric";

    // user
    public static final String SQL_GET_USER_BY_EMAIL = 
    "SELECT * FROM user WHERE user_email = ?"; 

    public static final String SQL_INSERT_USER = 
    "INSERT INTO user (user_id, user_first_name, user_last_name, user_email, user_mobile, user_password) VALUES (?,?,?,?,?,?)"; 
    
    public static final String SQL_INSERT_ADDRESS = 
    "INSERT INTO address (street, postal_code) VALUES (?,?)"; 

    public static final String SQL_GET_ADDRESS_ID = 
    "SELECT id FROM address ORDER BY id DESC LIMIT 1";

    public static final String SQL_INSERT_ORDER = 
    "INSERT INTO user_orders (order_tracking_number, quantity, total_price, date_created, user_id, shipping_address_id) VALUES (?,?,?,?,?,?)"; 

    public static final String SQL_GET_ORDER_ID = 
    "SELECT id FROM user_orders ORDER BY id DESC LIMIT 1";

    public static final String SQL_INSERT_ORDER_ITEM = 
    "INSERT INTO order_item (img_link, quantity, unit_price, prod_id, order_id) VALUES (?,?,?,?,?)"; 
}