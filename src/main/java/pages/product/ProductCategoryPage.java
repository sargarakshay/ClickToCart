package pages.product;

import exception.BusinessException;
import model.Customer;
import model.Product;
import org.apache.log4j.Logger;
import repository.ProductSearchRepository;
import service.ProductSearchService;

import java.util.List;
import java.util.Scanner;

public class ProductCategoryPage {
    private static final Logger log = Logger.getLogger(ProductCategoryPage.class);
    Scanner scanner = new Scanner(System.in);
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    ProductPage productPage = new ProductPage();

    public void viewProductByCategory(int productCategoryChoice, Customer customer) {
        if (customer.isCustomerLoginSession()) {
            switch (productCategoryChoice) {
                case 1:
                    int fashionProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|           Fashion            |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(1);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        fashionProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(fashionProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException | NumberFormatException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 2:
                    int mobileProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|           Mobiles            |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(2);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        mobileProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(mobileProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 3:
                    int electronicProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|         Electronics          |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(3);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        electronicProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(electronicProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 4:
                    int homeProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|             Home             |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(4);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        homeProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(homeProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 5:
                    int appliancesProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|         Appliances           |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(5);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        appliancesProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(appliancesProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 6:
                    int beautyProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|           Beauty             |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(6);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        beautyProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(beautyProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 7:
                    int toyProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|         Toys & Baby          |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(7);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        toyProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(toyProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 8:
                    int furnitureProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|          Furniture           |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(8);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        furnitureProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(furnitureProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 9:
                    int groceryProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|           Grocery            |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(9);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        groceryProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(groceryProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                case 10:
                    int foodProductChoice = 0;
                    log.info("+------------------------------+");
                    log.info("|         Food & more          |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    try {
                        List<Product> productList = productSearchRepository.searchProductByCategory(10);
                        if (productList != null) {
                            for (Product product: productList) {
                                log.info("| "+product.getProductId()+". "+product.getProductName());
                            }
                        }
                        log.info("+------------------------------+");
                        log.info("| Enter your choice...         |");
                        foodProductChoice = Integer.parseInt(scanner.nextLine());
                        log.info("+------------------------------+");
                        log.info("");
                        productPage.productDetails(foodProductChoice, productCategoryChoice, customer);
                    } catch (BusinessException e) {
                        log.warn(e.getMessage());
                    }
                    break;
                default:
                    log.warn("Invalid User Input : Please enter numbers between (1-10)...");
            }
        } else {
            log.info("Please login to continue");
            log.info("You will be redirected to your Login portal in 2 seconds...");
        }
    }
}
