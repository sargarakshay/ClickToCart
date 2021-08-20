package dao;

import exception.BusinessException;
import model.Customer;

import java.util.List;

public interface CustomerDAO {
    public int createCustomer(Customer customer) throws BusinessException;
    public int deleteCustomer(int customerId);
    public List<Customer> viewCustomer() throws BusinessException;
    public boolean isUsernameAlreadyExist(String customerUsername) throws BusinessException;
    public boolean isPasswordAlreadyExist(String customerUsername, String customerPassword) throws BusinessException;
}
