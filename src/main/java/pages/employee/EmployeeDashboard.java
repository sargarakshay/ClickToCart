package pages.employee;


import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import exception.BusinessException;
import model.Customer;
import model.Order;

import model.Product;
import org.apache.log4j.Logger;
import pages.customer.CustomerLogin;
import pages.order.OrderPage;
import pages.product.ProductPage;
import repository.CustomerRepository;
import repository.CustomerSearchRepository;
import repository.ProductSearchRepository;
import service.CustomerSearchService;
import service.CustomerService;
import service.ProductSearchService;

import java.util.List;
import java.util.Scanner;

public class EmployeeDashboard {
    private static final Logger log = Logger.getLogger(CustomerLogin.class);
    CustomerRepository customerRepository = new CustomerService();
    CustomerSearchRepository customerSearchRepository = new CustomerSearchService();
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    OrderPage orderPage = new OrderPage();
    OrderDAO orderDAO = new OrderDAOImpl();
    Scanner scanner = new Scanner(System.in);

    public void employeeDashboard() {
        int dashboardOption = 0;
        do {
            try {
                List<Customer> customerList = customerRepository.viewCustomer();
                List<Order> orderList = orderDAO.viewAllOrders();
                log.info("");
                log.info("+------------------------------+");
                log.info("|          Dashboard           |");
                log.info("+------------------------------+");
                log.info("| Hello, Admin Employee!!      |");
                log.info("| Welcome To Click To Cart...  |");
                log.info("|                              |");
                log.info("| Total Customers : " + customerList.size() + "         |");
                log.info("| Total Orders Placed : " + orderList.size() + "     |");
                log.info("|                              |");
                log.info("| 1. View Customers            |");
                log.info("| 2. View Orders               |");
                log.info("| 3. Manage Products           |");
                log.info("| 4. LogOut                    |");
                log.info("+------------------------------+");
                log.info("| Enter your choice :          |");
                try {
                    dashboardOption = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    log.warn(e.getMessage());
                }
                log.info("+------------------------------+");
                switch (dashboardOption) {
                    case 1:
                        int customerOption = 0;
                        do {
                            Thread.sleep(1000);
                            log.info("");
                            log.info("+------------------------------+");
                            log.info("|          Customers           |");
                            log.info("+------------------------------+");
                            log.info("| #  Customer Name             ");
                            log.info("|                              ");
                            for (Customer customer : customerList) {
                                log.info("| " + customer.getCustomerId() + ". " + customer.getCustomerName());
                            }
                            log.info("+------------------------------+");
                            log.info("| 1. Goto Dashboard            |");
                            log.info("+------------------------------+");
                            log.info("| Enter your choice...         |");
                            customerOption = Integer.parseInt(scanner.nextLine());
                            log.info("+------------------------------+\n");

                            if (customerOption == 1) {
                                log.info("Please wait, Redirecting to your Dashboard!");
                                Thread.sleep(1000);
                            } else {
                                log.warn("Invalid User Input : Please enter 1...");
                            }
                        } while (customerOption != 1);
                        break;
                    case 2:
                        int orderOption = 0;
                        Thread.sleep(1000);
                        log.info("");
                        log.info("+------------------------------+");
                        log.info("|           Orders             |");
                        log.info("+------------------------------+");
                        log.info("| #  Product Name              ");
                        log.info("|                              ");
                        Product product;
                        for (Order order : orderList) {
                            product = productSearchRepository.searchProductByProductId(order.getProduct().getProductId());
                            log.info("| " + order.getOrderId() + ". " + product.getProductName());
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        orderOption = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+\n");
                        orderPage.orderedProductDetailEmployee(orderOption);
                        break;
                    case 3:
                        log.info("Under Construction 3");
                        break;
                    case 4:
                        log.info("");
                        log.info("Oh no! You're leaving");
                        log.info("Bye!!");
                        log.info("");
                        System.exit(0);
                    default:
                        log.warn("Invalid User Input : Please enter numbers between (1-5)...");
                }
            } catch (InterruptedException | BusinessException | NumberFormatException e) {
                log.warn(e.getMessage());
            }
        } while (dashboardOption != 4);

    }
}
