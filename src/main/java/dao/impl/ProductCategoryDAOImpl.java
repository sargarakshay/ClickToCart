package dao.impl;

import dao.ProductCategoryDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.ProductCategory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {
    private static final Logger log = Logger.getLogger(ProductCategoryDAOImpl.class);

    @Override
    public List<ProductCategory> viewProductCategory() throws BusinessException {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT productCategoryId, productCategoryName FROM productCategory";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategory.setProductCategoryName(resultSet.getString("productCategoryName"));
                productCategoryList.add(productCategory);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e.getMessage());
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return productCategoryList;
    }

    @Override
    public ProductCategory getProductCategoryByProductCategoryId(int productCategoryId) throws BusinessException {
        ProductCategory productCategory = new ProductCategory();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT productCategoryId, productCategoryName FROM productCategory WHERE productCategoryId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productCategoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                productCategory.setProductCategoryId(resultSet.getInt("productCategoryId"));
                productCategory.setProductCategoryName(resultSet.getString("productCategoryName"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return productCategory;
    }

    @Override
    public int addCategory(ProductCategory productCategory) {
        return 0;
    }

    @Override
    public int deleteCategory(ProductCategory productCategory) {
        return 0;
    }
}
