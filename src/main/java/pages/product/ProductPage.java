package pages.product;

import dao.ProductCategoryDAO;
import dao.ProductDAO;
import dao.impl.ProductCategoryDAOImpl;
import dao.impl.ProductDAOImpl;
import exception.BusinessException;
import model.Customer;
import model.Product;
import model.ProductCategory;
import org.apache.log4j.Logger;
import pages.cart.CartPage;
import repository.ProductCategoryRepository;
import repository.ProductRepository;
import repository.ProductSearchRepository;
import service.ProductCategoryService;
import service.ProductSearchService;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductPage {
    private static final Logger log = Logger.getLogger(ProductCategoryPage.class);
    Scanner scanner = new Scanner(System.in);
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    ProductCategoryRepository productCategoryRepository = new ProductCategoryService();
    ProductCategoryDAO productCategoryDAO = new ProductCategoryDAOImpl();
    ProductRepository productRepository = new ProductService();
    ProductDAO productDAO = new ProductDAOImpl();
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
                log.info("| Product ID : " + product.getProductId());
                log.info("|                              ");
                log.info("| Name : " + product.getProductName());
                log.info("| Category : " + product.getCategory().getProductCategoryName());
                log.info("| Price : ₹" + product.getProductPrice());
                log.info("+------------------------------+");
                log.info("");
                log.info("+------------------------------+");
                log.info("| 1. Add To Cart               |");
                log.info("| 2. Goto Dashboard            |");
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
                            log.info("Please wait, Redirecting to your Cart!");
                            Thread.sleep(1000);
                        } else {
                            log.info("Product Quantity must be positive.");
                        }
                        break;
                    case 2:
                        log.info("Please wait, Redirecting to your Dashboard!");
                        Thread.sleep(1000);
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

    public void manageProducts() {
        int manageProductsChoice;
        log.info("");
        log.info("+------------------------------+");
        log.info("|       Manage Products        |");
        log.info("+------------------------------+");
        log.info("| 1. View Products             |");
        log.info("| 2. Add Products              |");
        log.info("| 3. Goto Dashboard            |");
        log.info("+------------------------------+");
        log.info("| Enter your choice :          |");
        manageProductsChoice = Integer.parseInt(scanner.nextLine());
        log.info("+------------------------------+");
        switch (manageProductsChoice) {
            case 1:
                try {
                    int productChoice;
                    List<Product> productList = productDAO.viewProduct();
                    log.info("+------------------------------+");
                    log.info("|          Products            |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    for (Product product : productList) {
                        log.info("| " + product.getProductId() + ". " + product.getProductName());
                    }
                    log.info("+------------------------------+");
                    log.info("| Enter your choice...         |");
                    productChoice = Integer.parseInt(scanner.nextLine());
                    log.info("+------------------------------+\n");
                    Product product = productSearchRepository.searchProductByProductId(productChoice);
                    int productDetailChoice = 0;
                    do {
                        if (productChoice > 0) {
                            log.info("");
                            log.info("+------------------------------+");
                            log.info("|       Product Details        |");
                            log.info("+------------------------------+");
                            log.info("| Product ID : " + product.getProductId());
                            log.info("|                              ");
                            log.info("| Name : " + product.getProductName());
                            log.info("| Category : " + product.getCategory().getProductCategoryName());
                            log.info("| Price : ₹" + product.getProductPrice());
                            log.info("+------------------------------+");
                            log.info("");
                            log.info("+------------------------------+");
                            log.info("| 1. Goto Dashboard            |");
                            log.info("+------------------------------+");
                            log.info("| Enter your choice :          |");
                            productDetailChoice = Integer.parseInt(scanner.nextLine());
                            log.info("+------------------------------+");
                        }
                    } while (productDetailChoice != 1);
                } catch (BusinessException e) {
                    log.warn(e.getMessage());
                }
                break;
            case 2:
                int isSucessfull = 0;
                do {
                    try {
                        Product product = new Product();
                        log.info("");
                        log.info("+-------------------------------- +");
                        log.info("|          Add Product            |");
                        log.info("+---------------------------------+");
                        log.info("| Enter Product Name :            |");
                        String productName = scanner.nextLine();
                        if (productName.length() > 5) {
                            product.setProductName(productName);
                            log.info("| Enter Price :                   |");
                            double productPrice = Double.parseDouble(scanner.nextLine());
                            if (productPrice > 0) {
                                product.setProductPrice(productPrice);
                                log.info("| Select Product Category :       |");
                                int productCategoryChoice;
                                List<ProductCategory> productCategoryList = productCategoryRepository.viewProductCategory();
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
                                if (productCategoryChoice > 0 && productCategoryChoice < 11) {
                                    ProductCategory productCategory = productCategoryDAO.getProductCategoryByProductCategoryId(productCategoryChoice);
                                    product.setCategory(productCategory);
                                    int productId = productRepository.createProduct(product);

                                    int viewProductDetailsChoice;
                                    if (productId != 0) {
                                        log.info("Product added successfully");
                                        log.info("Your product information appears below.");
                                        Thread.sleep(1000);
                                        do {
                                            log.info("");
                                            isSucessfull = 1;
                                            log.info("+------------------------------+");
                                            log.info("|       Product Details        |");
                                            log.info("+------------------------------+");
                                            log.info("| Product ID : " + productId);
                                            log.info("|                              ");
                                            log.info("| Name : " + product.getProductName());
                                            log.info("| Category : " + product.getCategory().getProductCategoryName());
                                            log.info("| Price : ₹" + product.getProductPrice());
                                            log.info("+------------------------------+");
                                            log.info("");
                                            log.info("+------------------------------+");
                                            log.info("| 1. Goto Dashboard            |");
                                            log.info("+------------------------------+");
                                            log.info("| Enter your choice :          |");
                                            viewProductDetailsChoice = Integer.parseInt(scanner.nextLine());
                                            log.info("+------------------------------+");
                                        } while (viewProductDetailsChoice != 1);
                                    }
                                } else {
                                    log.warn("Invalid User Input : Please enter numbers between (1-10)...");
                                }
                            } else {
                                log.warn("Invalid User Input : Please enter valid price.");
                            }
                        } else {
                            log.warn("Invalid User Input : Please enter valid product name.");
                        }
                    } catch (BusinessException | InterruptedException e) {
                        log.warn(e.getMessage());
                    }
                } while (isSucessfull != 1);
                break;
            default: log.warn("Invalid User Input : Please enter numbers between (1-2)...");
        }
    }
}
