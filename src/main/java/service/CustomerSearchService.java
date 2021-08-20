package service;

import dao.CustomerSearchDAO;
import dao.impl.CustomerSearchDAOImpl;
import exception.BusinessException;
import model.Customer;
import repository.CustomerSearchRepository;

public class CustomerSearchService implements CustomerSearchRepository {
    CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();

    @Override
    public Customer searchCustomerByCustomerUsername(String customerUsername) throws BusinessException {
        Customer customer;
        customer = customerSearchDAO.searchCustomerByCustomerUsername(customerUsername);

        return customer;
    }
}
