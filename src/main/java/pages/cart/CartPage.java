package pages.cart;

import exception.BusinessException;
import model.Cart;
import model.Customer;
import model.Product;
import org.apache.log4j.Logger;
import pages.order.OrderPage;
import repository.CartRepository;
import repository.CartSearchRepository;
import repository.ProductSearchRepository;
import service.CartSearchService;
import service.CartService;
import service.ProductSearchService;

import java.util.List;
import java.util.Scanner;

public class CartPage {
    private static final Logger log = Logger.getLogger(CartPage.class);
    CartRepository cartRepository = new CartService();
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    CartSearchRepository cartSearchRepository = new CartSearchService();
    OrderPage orderPage = new OrderPage();
    Scanner scanner = new Scanner(System.in);

    public void addProductToCard(int productQuantity, Customer customer, Product product) {
        int cartId;
        try {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setCustomer(customer);
            cart.setCartProductQuantity(productQuantity);
            cart.setCartProductTotal(product.getProductPrice() * productQuantity);

            cartId = cartRepository.addProductToCart(cart);
            if (cartId == 1) {
                log.info(product.getProductName()+" is added to your cart successfully!!!");
                Thread.sleep(1000);
            }
        } catch (BusinessException | InterruptedException e) {
            log.warn(e.getMessage());
        }
    }

    public void viewCart(Customer customer) {
        if (customer.isCustomerLoginSession()) {
            try {
                List<Cart> cartList = cartRepository.viewCart(customer);
                if (cartList.size() != 0) {
                    Product product;
                    int cartChoice;
                    log.info("");
                    log.info("+------------------------------+");
                    log.info("|           My Cart            |");
                    log.info("+------------------------------+");
                    log.info("| #  Product Name              ");
                    log.info("|                              ");
                    for (Cart cart : cartList) {
                        product = productSearchRepository.searchProductByProductId(cart.getProduct().getProductId());
                        log.info("| " + cart.getCartId() + ". " + product.getProductName());
                    }
                    log.info("+------------------------------+");
                    log.info("| Choose product to Buy...     |");
                    cartChoice = Integer.parseInt(scanner.nextLine());
                    log.info("+------------------------------+");
                    productToBuy(customer, cartChoice);
                } else {
                    Thread.sleep(500);
                    log.info("Your cart is empty!");
                    log.info("It's a good day to buy the items you saved for later!");
                    Thread.sleep(1000);
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public void productToBuy(Customer customer, int cartId) {
        if (customer.isCustomerLoginSession()) {
            int productBuyChoice;
            try {
                Cart cart = cartSearchRepository.searchCartByCartId(cartId);
                Product product = productSearchRepository.searchProductByProductId(cart.getProduct().getProductId());
                log.info("+------------------------------+");
                log.info("|         Cart Details         |");
                log.info("+------------------------------+");
                log.info("| Cart ID : " +cartId);
                log.info("|                              ");
                log.info("| Name : " +product.getProductName());
                log.info("| Category : " +product.getCategory().getProductCategoryName());
                log.info("| Quantity: "+cart.getCartProductQuantity());
                log.info("| Total Price : ₹" +cart.getCartProductTotal());
                log.info("+------------------------------+");
                log.info("");
                log.info("+------------------------------+");
                log.info("| 1. Buy Now                   |");
                log.info("| 2. Go back to Dashboard      |");
                log.info("+------------------------------+");
                log.info("| Enter your choice            |");
                productBuyChoice = Integer.parseInt(scanner.nextLine());
                log.info("+------------------------------+");
                log.info("");
                switch (productBuyChoice) {
                    case 1:
                        orderPage.addOrder(product, customer, cart);
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
        }
    }
}
