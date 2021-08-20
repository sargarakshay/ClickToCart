package repository;

import exception.BusinessException;
import model.ProductCategory;

import java.util.List;
import java.util.Locale;

public interface ProductCategoryRepository {
    public List<ProductCategory> viewProductCategory() throws BusinessException;
    public int addCategory(ProductCategory productCategory);
    public int deleteCategory(ProductCategory productCategory);
}
