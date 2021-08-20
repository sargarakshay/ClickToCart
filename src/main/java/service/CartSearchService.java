package service;

import dao.CartSearchDAO;
import dao.impl.CartSearchDAOImpl;
import exception.BusinessException;
import model.Cart;
import repository.CartSearchRepository;

public class CartSearchService implements CartSearchRepository {
    CartSearchDAO cartSearchDAO = new CartSearchDAOImpl();

    @Override
    public Cart searchCartByCartId(int cartId) throws BusinessException {
        Cart cart;
        cart = cartSearchDAO.searchCartByCartId(cartId);
        return cart;
    }
}
