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

    // search-related
    public static final String SQL_SELECT_ALL_PRODUCT_BY_QUERY = 
    "SELECT * FROM product WHERE prod_name LIKE CONCAT('%',?,'%')";
    
    // get product fabric
    public static final String SQL_SELECT_ALL_FABRIC =
    "SELECT * FROM fabric";
}