package pages.employee;

import model.Customer;
import org.apache.log4j.Logger;
import pages.customer.CustomerLogin;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.util.Scanner;

public class EmployeeLogin {
    private static final Logger log = Logger.getLogger(CustomerLogin.class);
    Scanner scanner = new Scanner(System.in);
    EmployeeRepository employeeRepository = new EmployeeService();
    EmployeeDashboard employeeDashboard = new EmployeeDashboard();
    public void employeeLogin() {

        boolean isSucessfull = false;
        do {
            Customer customer;
            log.info("");
            log.info("+-------------------------------- +");
            log.info("|        Employee Login           |");
            log.info("+--------------------------------+");
            log.info("| Welcome To Click To Cart...     |");
            log.info("| Please enter your details..     |");
            log.info("|                                 |");
            log.info("| Enter Username :                |");
            String employeeUsername = scanner.nextLine();
            try {
                if (employeeRepository.isEmployeeUsernameAlreadyExist(employeeUsername)) {
                    log.info("| Enter Password :                |");
                    String employeePassword = scanner.nextLine();
                    if (employeeRepository.isEmployeePasswordAlreadyExist(employeePassword)) {
                        log.info("|                                 |");
                        log.info("+---------------------------------+");
                        isSucessfull = true;
                        Thread.sleep(1000);
                        log.info("\nLogin Sucessfull!!!");
                        Thread.sleep(1000);
                        log.info("Please wait, Redirecting to your Dashboard!");
                        employeeDashboard.employeeDashboard();
                    } else {
                        log.info("|                                 |");
                        log.info("+---------------------------------+");
                        log.info("");
                        log.warn("The username or password you entered is incorrect.");
                        log.warn("Try again :)");
                        Thread.sleep(1000);
                    }
                } else {
                    log.info("|                                 |");
                    log.info("+---------------------------------+");
                    log.info("");
                    log.warn("That ClickToCart username doesn't exist.");
                    Thread.sleep(500);
                    log.warn("Enter a different username or get a new one.");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log.info("|                                 |");
                log.info("+---------------------------------+");
                log.info("");
                log.warn(e.getMessage());
                log.info("");
            }
        } while (!isSucessfull);
    }
}
