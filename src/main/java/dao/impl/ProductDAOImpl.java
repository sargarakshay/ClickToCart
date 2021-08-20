package dao.impl;
import dao.ProductDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    Logger log = Logger.getLogger(ProductDAOImpl.class);

    @Override
    public int createProduct(Product product) throws BusinessException {
        int isSucessfull = 0;
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "INSERT INTO product(productName, productPrice, productCategory) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getCategory().getProductCategoryId());

            isSucessfull = preparedStatement.executeUpdate();
            if (isSucessfull == 0) {
                throw new BusinessException("Customer creation failed! Check your query and try again!!!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return isSucessfull;
    }
}
