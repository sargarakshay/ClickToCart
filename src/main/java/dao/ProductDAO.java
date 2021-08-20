package dao;

import exception.BusinessException;
import model.Product;

import java.util.List;

public interface ProductDAO {
    public int createProduct(Product product) throws BusinessException;
}
