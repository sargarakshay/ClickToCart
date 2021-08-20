package service;

import dao.CartDAO;
import dao.impl.CartDAOImpl;
import exception.BusinessException;
import model.Cart;
import model.Customer;
import repository.CartRepository;

import java.util.List;

public class CartService implements CartRepository {
    CartDAO cartDAO = new CartDAOImpl();

    @Override
    public List<Cart> viewCart(Customer customer) throws BusinessException {
        List<Cart> cartList = null;
        cartList = cartDAO.viewCart(customer);
        return cartList;
    }

    @Override
    public int addProductToCart(Cart cart) throws BusinessException {
        int cartId = 0;
        cartId = cartDAO.addProductToCart(cart);
        return cartId;
    }

    @Override
    public int removeProductFromCart(int cartId) throws BusinessException {
        int isSucessfull;
        isSucessfull = cartDAO.removeProductFromCart(cartId);
        return isSucessfull;
    }
}
