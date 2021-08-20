package repository;

import exception.BusinessException;
import model.Cart;
import model.Customer;

import java.util.List;

public interface CartRepository {
    public List<Cart> viewCart(Customer customer) throws BusinessException;
    public int addProductToCart(Cart cart) throws BusinessException;
    public int removeProductFromCart(int cartId) throws BusinessException;
}
