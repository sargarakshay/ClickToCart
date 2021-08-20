package pages;
import org.apache.log4j.Logger;
import pages.customer.CustomerLogin;

import java.util.Scanner;

public class ClickToCart {
    private static final Logger log = Logger.getLogger(ClickToCart.class);
    public static void main(String[] args) {
        CustomerLogin customerLogin = new CustomerLogin();

        int menuChoice;
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                log.info("+-------------------------------- +");
                log.info("|    Welcome To Click To Cart     |");
                log.info("+---------------------------------+");
                Thread.sleep(1000);
                log.info("|     Come for what you love.     |");
                Thread.sleep(1000);
                log.info("|   Stay for what you discover.   |");
                Thread.sleep(1000);
                log.info("|                                 |");
                log.info("| 1. New Customer Registration    |");
                log.info("| 2. Customer Login               |");
                log.info("| 3. Employee Login               |");
                log.info("| 4. Exit                         |");
                log.info("+---------------------------------+");
                log.info("| Choose your option :            |");
                menuChoice = Integer.parseInt(scanner.nextLine());
                log.info("+---------------------------------+");
                switch (menuChoice) {
                    case 1:
                        customerLogin.registerCustomer();
                        break;
                    case 2:
                        customerLogin.customerLogin();
                        continue;
                    case 3:
                        log.info("Under Construction Employee Login");
                        break;
                    case 4:
                        log.info("Oh no! You're leaving");
                        log.info("Bye!!");
                        System.exit(0);
                        break;
                    default:
                        log.warn("Invalid Input : Please enter numbers between (1-3)...");
                }

            } while (menuChoice != 3);

        } catch (InterruptedException | NumberFormatException e) {
            log.warn(e.getMessage());
        }
    }
}
