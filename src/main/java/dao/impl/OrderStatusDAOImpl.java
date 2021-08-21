package dao.impl;

import dao.OrderStatusDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderStatusDAOImpl implements OrderStatusDAO {

    @Override
    public int updateOrderStatus(int orderStatusId, int orderId) throws BusinessException {
        int isSucessfull=0;
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "UPDATE `order` SET orderStatusId=? WHERE orderId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderStatusId);
            preparedStatement.setInt(2, orderId);
            isSucessfull = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return isSucessfull;
    }
}
