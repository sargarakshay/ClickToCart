package dao;

import exception.BusinessException;
import model.Cart;

public interface CartSearchDAO {
    public Cart searchCartByCartId(int cartId) throws BusinessException;

}
