package dao;

import exception.BusinessException;
import model.Order;

public interface OrderSearchDAO {
    public Order searchOrderByOrderId(int orderId) throws BusinessException;
    public Order searchOrderByCustomerId(int customerId) throws BusinessException;
}
