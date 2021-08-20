package service;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import exception.BusinessException;
import model.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService implements CustomerRepository {

    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public int createCustomer(Customer customer) throws BusinessException {
        int isSucessfull;
        if (customer.getCustomerName().length() != 0) {
            isSucessfull = customerDAO.createCustomer(customer);
        } else {
            throw new BusinessException("Invalid Customer Details");
        }
        return isSucessfull;
    }

    @Override
    public int deleteCustomer(int customerId) {
        return 0;
    }

    @Override
    public List<Customer> viewCustomer() throws BusinessException {
        List<Customer> customerList;
        customerList = customerDAO.viewCustomer();
        return customerList;
    }

    @Override
    public boolean isValidCustomerUsername(String customerUsername) throws BusinessException {
        if (customerUsername.matches("[a-z]\\w{6,30}")) {
                return true;
        } else {
            throw new BusinessException("The username you entered is not valid.\n\nYour username needs to:\n * include only lowercase characters.\n * be at least 5 characters long.");
        }
    }

    @Override
    public boolean isValidCustomerPassword(String customerPassword) throws BusinessException {
        if (customerPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$")) {
            return true;
        }
        throw new BusinessException("The password you entered is not valid. \n\nYour password needs to:\n * include both lower and uppercase characters.\n * include at least one number or symbol.\n * be at least 8 characters long.");
    }
}
