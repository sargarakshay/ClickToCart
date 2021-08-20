import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import exception.BusinessException;
import static org.junit.Assert.*;
import org.junit.*;
import pages.customer.CustomerLogin;
import repository.CustomerRepository;
import service.CustomerService;

public class TestLogic {
    CustomerLogin customerLogin;
    CustomerRepository customerRepository;
    CustomerDAO customerDAO;

    @Before
    public void setUp() {
       customerLogin = new CustomerLogin();
       customerRepository = new CustomerService();
        customerDAO = new CustomerDAOImpl();
    }

    @Test
    public void customerUsernameValidationWithCorrectData() {
        try {
            assertTrue(customerRepository.isValidCustomerUsername("akshaysargar"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerUsernameValidationWithIncorrectData() {
        try {
            assertFalse(customerRepository.isValidCustomerUsername("1"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerPasswordValidationWithCorrectData() {
        try {
            assertTrue(customerRepository.isValidCustomerPassword("Akshay@123"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerPasswordValidationWithIncorrectData() {
        try {
            assertFalse(customerRepository.isValidCustomerPassword("Akshay"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerUsernameAlreadyExistWithCorrectData() {
        try {
            assertTrue(customerDAO.isUsernameAlreadyExist("akshaysargar"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerUsernameAlreadyExistWithIncorrectData() {
        try {
            assertFalse(customerDAO.isUsernameAlreadyExist("sargarakshay"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerPasswordAlreadyExistWithCorrectData() {
        try {
            assertTrue(customerDAO.isPasswordAlreadyExist("akshaysargar","Akshay@123"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void customerPasswordAlreadyExistWithIncorrectData() {
        try {
            assertFalse(customerDAO.isPasswordAlreadyExist("akshaysargar", "Sargar@123"));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }



}
