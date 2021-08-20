package repository;

import exception.BusinessException;
import model.Cart;

public interface CartSearchRepository {
    public Cart searchCartByCartId(int cartId) throws BusinessException;
}
