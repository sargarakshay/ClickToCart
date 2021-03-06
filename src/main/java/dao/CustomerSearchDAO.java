package dao;

import exception.BusinessException;
import model.Customer;

public interface CustomerSearchDAO {
    public Customer searchCustomerByCustomerUsername(String customerUsername) throws BusinessException;
    public Customer searchCustomerByCustomerId(int customerId) throws BusinessException;
}
