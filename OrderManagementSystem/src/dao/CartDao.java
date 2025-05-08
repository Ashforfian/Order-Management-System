package dao;

import core.Database;
import entity.Cart;
import entity.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CartDao {
    private Connection connection;
    private ProductDao productDao;
    private CustomerDao customerDao;

    public CartDao() {
        this.customerDao = new CustomerDao();
        this.productDao = new ProductDao();
        this.connection = Database.getInstance();
    }

    public ArrayList<Cart> findAll(){
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM cart");
            while (rs.next()){
                carts.add(this.match(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carts;
    }

    public Cart match(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getInt("id"));
        cart.setCustomerId(rs.getInt("customer_id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setPrice(rs.getInt("price"));
        cart.setNote(rs.getString("note"));
        cart.setDate(LocalDate.parse(rs.getString("date")));
        cart.setCustomer(this.customerDao.getById(cart.getCustomerId()));
        cart.setProduct(this.productDao.getById(cart.getProductId()));

        return cart;


    }

    public boolean save(Cart cart){
        String query = "INSERT INTO cart (product_id,customer_id,price,note,date) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, cart.getProductId());
            pr.setInt(2, cart.getCustomerId());
            pr.setInt(3, cart.getPrice());
            pr.setString(4, cart.getNote());
            pr.setDate(5, Date.valueOf(cart.getDate()));

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
