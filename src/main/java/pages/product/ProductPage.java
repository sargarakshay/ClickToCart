package pages.product;

import exception.BusinessException;
import model.Customer;
import model.Product;
import org.apache.log4j.Logger;
import pages.cart.CartPage;
import repository.ProductSearchRepository;
import service.ProductSearchService;

import java.util.Scanner;

public class ProductPage {
    private static final Logger log = Logger.getLogger(ProductCategoryPage.class);
    Scanner scanner = new Scanner(System.in);
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    CartPage cartPage = new CartPage();

    public void productDetails(int productChoice, int productCategoryChoice, Customer customer) {
        Product product;
        int productCartChoice;
        if (customer.isCustomerLoginSession()) {
            try {
                product = productSearchRepository.searchProductByProductAndCategory(productChoice, productCategoryChoice);
                log.info("+------------------------------+");
                log.info("|       Product Details        |");
                log.info("+------------------------------+");
                log.info("| Product ID : " +product.getProductId());
                log.info("|                              ");
                log.info("| Name : " +product.getProductName());
                log.info("| Category : " +product.getCategory().getProductCategoryName());
                log.info("| Price : ₹" +product.getProductPrice());
                log.info("+------------------------------+");
                log.info("");
                log.info("+------------------------------+");
                log.info("| 1. Add To Cart               |");
                log.info("| 2. Go back to Dashboard      |");
                log.info("+------------------------------+");
                log.info("| Enter your choice            |");
                productCartChoice = Integer.parseInt(scanner.nextLine());
                log.info("+------------------------------+");
                log.info("");
                switch (productCartChoice) {
                    case 1:
                        int productQuantity;
                        log.info("+------------------------------+");
                        log.info("| Add Product Quantity         |");
                        log.info("+------------------------------+");
                        log.info("| Enter your choice            |");
                        productQuantity = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        if (productQuantity > 0) {
                            cartPage.addProductToCard(productQuantity, customer, product);
                            log.info("Please wait...Redirecting to your Cart");
                            Thread.sleep(1500);
                        } else {
                            log.info("Product Quantity must be positive.");
                        }
                        break;
                    case 2:
                        log.info("Please wait...Redirecting to your Dashboard");
                        Thread.sleep(1500);
                        break;
                    default:
                        log.warn("Invalid User Input : Please enter numbers between (1-2)...");
                }
            } catch (BusinessException | NumberFormatException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        } else {
            log.info("Please login to continue");
            log.info("You will be redirected to your Login portal in 2 seconds...");
        }
    }
}
