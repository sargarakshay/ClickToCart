package dao.impl;

import dao.ProductSearchDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.Product;
import model.ProductCategory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchDAOImpl implements ProductSearchDAO {
    private static final Logger log = Logger.getLogger(ProductSearchDAOImpl.class);
    @Override
    public List<Product> searchProductByCategory(int productCategory) throws BusinessException {
        List<Product> productList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT pr.productId, pr.productName, pr.productPrice, pc.productCategoryId, pc.productCategoryName FROM product pr JOIN productCategory pc ON pr.productCategoryId = pc.productCategoryId WHERE pr.productCategoryId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                ProductCategory productCategoryObj = new ProductCategory();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductPrice(resultSet.getDouble("productPrice"));
                productCategoryObj.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategoryObj.setProductCategoryName(resultSet.getString("productCategoryName"));
                product.setCategory(productCategoryObj);
                productList.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return productList;
    }

    @Override
    public Product searchProductByProductId(int productId) throws BusinessException {
        Product product = new Product();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT pr.productId, pr.productName, pr.productPrice, pc.productCategoryId, pc.productCategoryName FROM product pr JOIN productCategory pc ON pr.productCategoryId = pc.productCategoryId WHERE pr.productId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProductCategory productCategoryObj = new ProductCategory();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductPrice(resultSet.getDouble("productPrice"));
                productCategoryObj.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategoryObj.setProductCategoryName(resultSet.getString("productCategoryName"));
                product.setCategory(productCategoryObj);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return product;
    }

    @Override
    public Product searchProductByProductAndCategory(int productChoice, int productCategoryChoice) throws BusinessException {
        Product product = new Product();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT pr.productId, pr.productName, pr.productPrice, pr.productCategoryId, pc.productCategoryName FROM product pr JOIN productCategory pc ON pr.productCategoryId = pc.productCategoryId WHERE pr.productId = ? AND pr.productCategoryId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productChoice);
            preparedStatement.setInt(2, productCategoryChoice);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                ProductCategory productCategoryObj = new ProductCategory();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductPrice(resultSet.getDouble("productPrice"));
                productCategoryObj.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategoryObj.setProductCategoryName(resultSet.getString("productCategoryName"));
                product.setCategory(productCategoryObj);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return product;
    }
}
