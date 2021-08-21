package dao;

import exception.BusinessException;
import model.Cart;
import model.Customer;
import model.Order;
import model.Product;

import java.util.List;

public interface OrderDAO {
    public int addProductToOrder(Order order) throws BusinessException;
    public List<Order> viewOrder(Customer customer) throws BusinessException;
    public List<Order> viewAllOrders() throws BusinessException;
}
