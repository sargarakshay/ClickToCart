package service;

import dao.ProductCategoryDAO;
import dao.impl.ProductCategoryDAOImpl;
import exception.BusinessException;
import model.ProductCategory;
import org.apache.log4j.Logger;
import repository.ProductCategoryRepository;

import java.util.List;

public class ProductCategoryService implements ProductCategoryRepository {
    private static final Logger log = Logger.getLogger(ProductCategoryService.class);
    ProductCategoryDAO productCategoryDAO = new ProductCategoryDAOImpl();

    @Override
    public List<ProductCategory> viewProductCategory() throws BusinessException {
        List<ProductCategory> productCategoryList;
        productCategoryList = productCategoryDAO.viewProductCategory();
        if (productCategoryList != null) {
            return productCategoryList;
        } else {
            throw new BusinessException("No Category Found!! Add some Category and try again...");
        }
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
