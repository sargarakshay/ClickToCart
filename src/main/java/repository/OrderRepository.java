package repository;

import com.sun.org.apache.xpath.internal.operations.Or;
import exception.BusinessException;
import model.Cart;
import model.Customer;
import model.Order;
import model.Product;

import java.util.List;

public interface OrderRepository {
    public int addProductToOrder(Order order) throws BusinessException;
    public List<Order> viewOrder(Customer customer) throws BusinessException;
}
