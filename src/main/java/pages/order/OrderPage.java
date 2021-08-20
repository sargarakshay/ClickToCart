package pages.order;

import exception.BusinessException;
import model.*;
import org.apache.log4j.Logger;
import pages.cart.CartPage;
import repository.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class OrderPage {
    private static final Logger log = Logger.getLogger(CartPage.class);
    OrderRepository orderRepository = new OrderService();
    ProductSearchRepository productSearchRepository = new ProductSearchService();
    OrderSearchRepository orderSearchRepository = new OrderSearchService();
    CartRepository cartRepository = new CartService();
    Scanner scanner = new Scanner(System.in);
    public void addOrder(Product product, Customer customer, Cart cart) {
        if (customer.isCustomerLoginSession()) {
            int orderId;
            int isSucessfull;
            Order order = new Order();
            try {
                order.setProduct(product);
                order.setCustomer(customer);
                order.setOrderQuantity(cart.getCartProductQuantity());
                order.setOrderTotal(cart.getCartProductTotal());
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setOrderStatusId(1);
                order.setOrderStatus(orderStatus);
                orderId = orderRepository.addProductToOrder(order);
                if (orderId != 0) {
                    isSucessfull = cartRepository.removeProductFromCart(cart.getCartId());
                    if (isSucessfull == 1) {
                        log.info("Order Successfully Placed");
                        log.info("Thank you for ordering.");
                        Thread.sleep(1500);
                        log.info("");
                        log.info("Your order information appears below.");
                        log.info("+------------------------------+");
                        log.info("|         Order Details        |");
                        log.info("+------------------------------+");
                        log.info("| Order ID : " + orderId);
                        log.info("|                              ");
                        log.info("| Name : " + order.getProduct().getProductName());
                        log.info("| Category : " + order.getProduct().getCategory().getProductCategoryName());
                        log.info("| Quantity: " + order.getOrderQuantity());
                        log.info("| Total Price : ₹" + order.getOrderTotal());
                        log.info("| Status : Ordered");
                        log.info("+------------------------------+");
                        log.info("");
                        Thread.sleep(500);
                        log.info("Happy Shopping!!!");
                        Thread.sleep(1000);
                    }
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        } else {
            log.info("Please login to continue");
            log.info("You will be redirected to your Login portal in 2 seconds...");
        }
    }

    public void viewOrder(Customer customer) {
        if (customer.isCustomerLoginSession()) {
            try {
                List<Order> orderList = orderRepository.viewOrder(customer);
                if (orderList.size() != 0) {
                    Product product;
                    int orderChoice;
                  log.info("\n+--------------------------------+");
                    log.info("|           My Orders            |");
                    log.info("+--------------------------------+");
                    log.info("| #  Product Name             ");
                    log.info("|                              ");
                    for (Order order: orderList) {
                        product = productSearchRepository.searchProductByProductId(order.getProduct().getProductId());
                        log.info("| "+order.getOrderId()+". "+product.getProductName());
                    }
                    log.info("+------------------------------+");
                    log.info("| Choose product to get status |");
                    orderChoice = Integer.parseInt(scanner.nextLine());
                    log.info("+------------------------------+");
                    log.info("");
                    orderedProductDetail(customer, orderChoice);
                } else {
                    Thread.sleep(500);
                    log.info("");
                    log.info("You have no order!");
                    log.info("It's a good day to buy the items you saved for later!");
                    Thread.sleep(1000);
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public void orderedProductDetail(Customer customer, int orderId) {
        if (customer.isCustomerLoginSession()) {
            int orderedProductDetailChoice;
            try {
                Order order = orderSearchRepository.searchOrderByOrderId(orderId);
                Product product = productSearchRepository.searchProductByProductId(order.getProduct().getProductId());
                log.info("+------------------------------+");
                log.info("|         Order Details        |");
                log.info("+------------------------------+");
                log.info("| Order ID : " + orderId);
                log.info("|                              ");
                log.info("| Name : " + product.getProductName());
                log.info("| Category : " + product.getCategory().getProductCategoryName());
                log.info("| Quantity: " + order.getOrderQuantity());
                log.info("| Total Price : ₹" + order.getOrderTotal());
                log.info("| Order Status : " + order.getOrderStatus().getOrderStatusType());
                log.info("+------------------------------+");
                log.info("");
                log.info("+------------------------------+");
                log.info("| 1. Received? Change status   |");
                log.info("| 2. Go back to Dashboard      |");
                log.info("+------------------------------+");
                log.info("| Enter your choice            |");
                orderedProductDetailChoice = Integer.parseInt(scanner.nextLine());
                log.info("+------------------------------+");
                log.info("");
                switch (orderedProductDetailChoice) {
                    case 1:
                        //TODO change the order status.
                        if (order.getOrderStatus().getOrderStatusId() == 3) {
                            //TODO update status
                            log.info("Order Delivered Successfully");
                            log.info("Thank you for Shopping!!.");
                        } else {
                            log.info("Your order is still under " +order.getOrderStatus().getOrderStatusType() +" status");
                            log.info("Wait until it's Out for Delivery...");
                        }
                        Thread.sleep(1500);
                        break;
                    case 2:
                        log.info("Please wait...Redirecting to your Dashboard.");
                        Thread.sleep(1500);
                        break;
                    default:
                        log.warn("Invalid User Input : Please enter numbers between (1-2)...");
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }

}
