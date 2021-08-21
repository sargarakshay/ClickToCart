package dao;

import exception.BusinessException;

public interface OrderStatusDAO {
    public int updateOrderStatus(int orderStatusId, int orderId) throws BusinessException;
}
