package service;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import exception.BusinessException;
import model.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService implements ProductRepository {
    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public int createProduct(Product product) throws BusinessException {
        int productId;
        if (product.getProductName().length() != 0) {
            productId = productDAO.createProduct(product);
        } else {
            throw new BusinessException("Invalid Product Details");
        }
        return productId;
    }

    @Override
    public int deleteProduct(int productId) {
        return 0;
    }

    @Override
    public List<Product> viewProduct() {
        return null;
    }
}
