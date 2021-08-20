package repository;

import exception.BusinessException;
import model.Customer;

import java.util.List;

public interface CustomerRepository {
    public int createCustomer(Customer customer) throws BusinessException;
    public int deleteCustomer(int customerId);
    public List<Customer> viewCustomer() throws BusinessException;
    public boolean isValidCustomerUsername(String customerUsername) throws BusinessException;
    public boolean isValidCustomerPassword(String customerPassword) throws BusinessException;
}
