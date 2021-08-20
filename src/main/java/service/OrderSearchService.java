package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderSearchDAO;
import dao.impl.OrderSearchDAOImpl;
import exception.BusinessException;
import model.Order;
import repository.OrderSearchRepository;

public class OrderSearchService implements OrderSearchRepository {
    OrderSearchDAO orderSearchDAO = new OrderSearchDAOImpl();

    @Override
    public Order searchOrderByOrderId(int orderId) throws BusinessException {
        Order order;
        order = orderSearchDAO.searchOrderByOrderId(orderId);
        return order;
    }

    @Override
    public Order searchOrderByCustomerId(int customerId) throws BusinessException {
        Order order;
        order = orderSearchDAO.searchOrderByOrderId(customerId);
        return order;
    }
}
