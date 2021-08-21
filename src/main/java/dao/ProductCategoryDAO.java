package dao;

import exception.BusinessException;
import model.ProductCategory;

import java.util.List;

public interface ProductCategoryDAO {
    public List<ProductCategory> viewProductCategory() throws BusinessException;
    public ProductCategory getProductCategoryByProductCategoryId(int productCategoryId) throws BusinessException;
    public int addCategory(ProductCategory productCategory);
    public int deleteCategory(ProductCategory productCategory);
}
