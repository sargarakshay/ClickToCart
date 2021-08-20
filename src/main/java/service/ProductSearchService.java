package service;

import dao.ProductSearchDAO;
import dao.impl.ProductSearchDAOImpl;
import exception.BusinessException;
import model.Product;
import repository.ProductSearchRepository;

import java.util.List;

public class ProductSearchService implements ProductSearchRepository {
    ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();
    @Override
    public List<Product> searchProductByCategory(int productCategory) throws BusinessException {
        List<Product> productList;
        if(productCategory > 0 && productCategory < 11) {
            productList = productSearchDAO.searchProductByCategory(productCategory);
        } else {
            throw new BusinessException("Invalid Product Category : Please enter numbers between (1-10)...");
        }
        return productList;
    }

    @Override
    public Product searchProductByProductId(int productId) throws BusinessException {
        Product product;
        product = productSearchDAO.searchProductByProductId(productId);
        return product;
    }

    @Override
    public Product searchProductByProductAndCategory(int productChoice, int productCategoryChoice) throws BusinessException {
        Product product;
        if (productCategoryChoice > 0 && productCategoryChoice < 11) {
            product = productSearchDAO.searchProductByProductAndCategory(productChoice,productCategoryChoice);
        } else {
            throw new BusinessException("Invalid Product Category : Please enter numbers between (1-10)...");
        }
        return product;
    }
}
