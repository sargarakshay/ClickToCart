package repository;

import exception.BusinessException;
import model.Cart;
import model.Order;

public interface OrderSearchRepository {
    public Order searchOrderByOrderId(int orderId) throws BusinessException;
    public Order searchOrderByCustomerId(int customerId) throws BusinessException;
}
