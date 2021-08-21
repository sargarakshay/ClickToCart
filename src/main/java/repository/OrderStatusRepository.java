package repository;

import exception.BusinessException;

public interface OrderStatusRepository {
    public int updateOrderStatus(int orderStatusId, int orderId) throws BusinessException;
}
