package dao.impl;

import dao.CartSearchDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.Cart;
import model.Customer;
import model.Product;
import org.apache.log4j.Logger;
import pages.cart.CartPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartSearchDAOImpl implements CartSearchDAO {
    private static final Logger log = Logger.getLogger(CartPage.class);

    @Override
    public Cart searchCartByCartId(int cartId) throws BusinessException {
        Cart cart = new Cart();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT cartId, cartProductId, cartCustomerId, cartProductQuantity, cartProductTotal FROM cart WHERE cartId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                Product product = new Product();
                cart.setCartId(resultSet.getInt("cartId"));
                cart.setCartProductQuantity(resultSet.getInt("cartProductQuantity"));
                cart.setCartProductTotal(resultSet.getDouble("cartProductTotal"));
                product.setProductId(resultSet.getInt("cartProductId"));
                customer.setCustomerId(resultSet.getInt("cartCustomerId"));
                cart.setProduct(product);
                cart.setCustomer(customer);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return cart;
    }
}
