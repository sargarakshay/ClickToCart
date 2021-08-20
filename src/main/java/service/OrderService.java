package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import exception.BusinessException;
import model.Cart;
import model.Customer;
import model.Order;
import model.Product;
import repository.OrderRepository;

import java.util.List;

public class OrderService implements OrderRepository {

    OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public int addProductToOrder(Order order) throws BusinessException {
        int orderId;
        orderId = orderDAO.addProductToOrder(order);
        return orderId;
    }

    @Override
    public List<Order> viewOrder(Customer customer) throws BusinessException {
        List<Order> orderList;
        orderList = orderDAO.viewOrder(customer);
        return orderList;
    }


}
