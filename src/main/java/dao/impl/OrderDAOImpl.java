package dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger log = Logger.getLogger(OrderDAOImpl.class);
    @Override
    public int addProductToOrder(Order order) throws BusinessException {
        int isSucessfull;
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "INSERT INTO `order`(orderCustomerId, orderProductId, orderQuantity, orderTotal, orderStatusId) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomer().getCustomerId());
            preparedStatement.setInt(2, order.getProduct().getProductId());
            preparedStatement.setInt(3, order.getOrderQuantity());
            preparedStatement.setDouble(4, order.getOrderTotal());
            preparedStatement.setInt(5, order.getOrderStatus().getOrderStatusId());

            isSucessfull = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return isSucessfull;
    }

    @Override
    public List<Order> viewOrder(Customer customer) throws BusinessException {
        List<Order> orderList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT o.orderId, o.orderCustomerId, o.orderProductId, o.orderQuantity, o.orderTotal, o.orderStatusId, os.orderStatusType FROM `order` o JOIN orderstatus os ON o.orderStatusId = os.orderStatusId WHERE o.orderCustomerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getCustomerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                Product product = new Product();
                Customer customerObj = new Customer();
                OrderStatus orderStatus = new OrderStatus();
                order.setOrderId(resultSet.getInt("orderId"));
                customerObj.setCustomerId(resultSet.getInt("orderCustomerId"));
                product.setProductId(resultSet.getInt("orderProductId"));
                order.setOrderQuantity(resultSet.getInt("orderQuantity"));
                order.setOrderTotal(resultSet.getDouble("orderTotal"));
                orderStatus.setOrderStatusId(resultSet.getInt("orderStatusId"));
                orderStatus.setOrderStatusType(resultSet.getString("orderStatusType"));

                order.setOrderStatus(orderStatus);
                order.setCustomer(customerObj);
                order.setProduct(product);
                orderList.add(order);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return orderList;
    }

    @Override
    public List<Order> viewAllOrders() throws BusinessException {
        List<Order> orderList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT o.orderId, o.orderCustomerId, o.orderProductId, o.orderQuantity, o.orderTotal, o.orderStatusId, os.orderStatusType FROM `order` o JOIN orderstatus os ON o.orderStatusId = os.orderStatusId ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                Product product = new Product();
                Customer customerObj = new Customer();
                OrderStatus orderStatus = new OrderStatus();
                order.setOrderId(resultSet.getInt("orderId"));
                customerObj.setCustomerId(resultSet.getInt("orderCustomerId"));
                product.setProductId(resultSet.getInt("orderProductId"));
                order.setOrderQuantity(resultSet.getInt("orderQuantity"));
                order.setOrderTotal(resultSet.getDouble("orderTotal"));
                orderStatus.setOrderStatusId(resultSet.getInt("orderStatusId"));
                orderStatus.setOrderStatusType(resultSet.getString("orderStatusType"));

                order.setOrderStatus(orderStatus);
                order.setCustomer(customerObj);
                order.setProduct(product);
                orderList.add(order);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return orderList;
    }
}
