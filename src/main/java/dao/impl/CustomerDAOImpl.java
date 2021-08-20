package dao.impl;

import dao.CustomerDAO;
import dao.utildb.MySQLDBConnection;
import exception.BusinessException;
import model.Customer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final Logger log = Logger.getLogger(CustomerDAOImpl.class);

    @Override
    public int createCustomer(Customer customer) throws BusinessException {
        int isSucessfull;
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "INSERT INTO Customer(customerName, customerUsername, customerPassword) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerUsername());
            preparedStatement.setString(3, customer.getCustomerPassword());

            isSucessfull = preparedStatement.executeUpdate();
            if (isSucessfull == 0) {
                throw new BusinessException("Customer creation failed! Check your query and try again!!!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return isSucessfull;
    }

    @Override
    public int deleteCustomer(int customerId) {
        return 0;
    }

    @Override
    public List<Customer> viewCustomer() throws BusinessException {
        List<Customer> customerList = new ArrayList<>();
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT customerId, customerName, customerUsername, customerPassword FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setCustomerUsername(resultSet.getString("customerUsername"));
                customer.setCustomerPassword(resultSet.getString("customerPassword"));
                customerList.add(customer);
            }
            if (customerList.size() == 0) {
                throw new BusinessException("Customer Database is Empty");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return customerList;
    }

    @Override
    public boolean isUsernameAlreadyExist(String customerUsername) throws BusinessException {
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT customerUsername FROM Customer WHERE customerUsername = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return false;
    }

    @Override
    public boolean isPasswordAlreadyExist(String customerUsername, String customerPassword) throws BusinessException {
        try(Connection connection = MySQLDBConnection.getConnection()) {
            String sql = "SELECT customerPassword FROM Customer WHERE customerPassword = ? AND customerUsername = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerPassword);
            preparedStatement.setString(2, customerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return false;
    }
}
