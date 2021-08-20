package pages.customer;

import exception.BusinessException;
import model.Customer;
import model.ProductCategory;
import org.apache.log4j.Logger;
import pages.cart.CartPage;
import pages.order.OrderPage;
import pages.product.ProductCategoryPage;
import repository.ProductCategoryRepository;
import service.ProductCategoryService;

import java.util.List;
import java.util.Scanner;

public class CustomerDashboard {
    private static final Logger log = Logger.getLogger(CustomerDashboard.class);
    Scanner scanner = new Scanner(System.in);
    ProductCategoryRepository productCategoryRepository = new ProductCategoryService();
    ProductCategoryPage productCategoryPage = new ProductCategoryPage();
    OrderPage orderPage = new OrderPage();
    CartPage cartPage = new CartPage();

    public void customerDashboard(Customer customer) {
        if (customer.isCustomerLoginSession()) {
            int dashboardOption = 0;
            do {
                try {
                    Thread.sleep(1000);
                    log.info("");
                    log.info("+------------------------------+");
                    log.info("|          Dashboard           |");
                    log.info("+------------------------------+");
                    log.info("| Hello..!!                    |");
                    log.info("| Welcome To Click To Cart...  |");
                    log.info("|                              |");
                    log.info("| 1. My Account                |");
                    log.info("| 2. My Orders                 |");
                    log.info("| 3. My Cart                   |");
                    log.info("| 4. All Categories            |");
                    log.info("| 5. LogOut                    |");
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
                            int myAccountOption = 0;
                            do {
                                Thread.sleep(1000);
                                log.info("");
                                log.info("+------------------------------+");
                                log.info("|          My Account          |");
                                log.info("+------------------------------+");
                                log.info("| Hello!!, " + customer.getCustomerName());
                                log.info("|                              |");
                                log.info("|                              |");
                                log.info("+------------------------------+");
                                log.info("| 1. Goto Dashboard            |");
                                log.info("+------------------------------+");
                                log.info("| Enter your choice...         |");
                                try {
                                    myAccountOption = Integer.parseInt(scanner.nextLine());
                                    log.info("+------------------------------+\n");
                                } catch (NumberFormatException e) {
                                    log.warn(e.getMessage());
                                }

                                if (myAccountOption == 1) {
                                    log.info("Back to Dashboard");
                                } else {
                                    log.warn("Invalid User Input : Please enter numbers between (1-5)...");
                                }

                            } while (myAccountOption != 1);
                            break;
                        case 2:
                            orderPage.viewOrder(customer);
                            break;
                        case 3:
                            cartPage.viewCart(customer);
                            break;
                        case 4:
                            int productCategoryChoice;
                            try {
                                List<ProductCategory> productCategoryList = productCategoryRepository.viewProductCategory();
                                log.info("");
                                Thread.sleep(1000);
                                log.info("+------------------------------+");
                                log.info("|         All Categories       |");
                                log.info("+------------------------------+");
                                log.info("| #  Category Name             ");
                                log.info("|                              ");
                                for (ProductCategory productCategory : productCategoryList) {
                                    log.info("| " + productCategory.getProductCategoryId() + ". " + productCategory.getProductCategoryName());
                                }
                                log.info("+------------------------------+");
                                log.info("| Enter your choice            |");
                                productCategoryChoice = Integer.parseInt(scanner.nextLine());
                                log.info("+------------------------------+");
                                log.info("");
                                productCategoryPage.viewProductByCategory(productCategoryChoice, customer);
                            } catch (BusinessException | InterruptedException e) {
                                log.warn(e.getMessage());
                            }
                            break;
                        case 5:
                            customer.setCustomerLoginSession(false);
                            log.info("");
                            log.info("Oh no! You're leaving");
                            log.info("Bye!!");
                            log.info("");
                            System.exit(0);
                        default:
                            log.warn("Invalid User Input : Please enter numbers between (1-5)...");
                    }
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            } while (dashboardOption != 5);
        } else {
            log.info("Please login to continue");
            log.info("You will be redirected to your Login portal in 2 seconds...");
        }
        scanner.close();
    }
}
