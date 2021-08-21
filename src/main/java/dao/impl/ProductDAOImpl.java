package dao.impl;
import dao.ProductDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.Product;
import model.ProductCategory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    Logger log = Logger.getLogger(ProductDAOImpl.class);

    @Override
    public int createProduct(Product product) throws BusinessException {
        int isSucessfull = 0;
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "INSERT INTO product(productName, productPrice, productCategoryId) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getCategory().getProductCategoryId());

            isSucessfull = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            if (isSucessfull == 0) {
                throw new BusinessException("Customer creation failed! Check your query and try again!!!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return isSucessfull;
    }

    @Override
    public List<Product> viewProduct() {
        List<Product> productList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT pr.productId, pr.productName, pr.productPrice, pc.productCategoryId, pc.productCategoryName FROM product pr JOIN productCategory pc ON pr.productCategoryId = pc.productCategoryId";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                ProductCategory productCategory = new ProductCategory();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductPrice(resultSet.getDouble("productPrice"));
                productCategory.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategory.setProductCategoryName(resultSet.getString("productCategoryName"));
                product.setCategory(productCategory);
                productList.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
