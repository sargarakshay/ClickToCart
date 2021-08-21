package repository;

import exception.BusinessException;
import model.Customer;

public interface CustomerSearchRepository {
    public Customer searchCustomerByCustomerUsername(String customerUsername) throws BusinessException;
    public Customer searchCustomerByCustomerId(int customerId) throws BusinessException;
}
