package service;

import dao.OrderStatusDAO;
import dao.impl.OrderStatusDAOImpl;
import exception.BusinessException;
import repository.OrderStatusRepository;

public class OrderStatusService implements OrderStatusRepository {
    OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    @Override
    public int updateOrderStatus(int orderStatusId, int orderId) throws BusinessException {
        int isSucessfull;
        isSucessfull = orderStatusDAO.updateOrderStatus(orderStatusId, orderId);
        return isSucessfull;
    }
}
