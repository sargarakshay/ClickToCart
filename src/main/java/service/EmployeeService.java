package service;

import repository.EmployeeRepository;

public class EmployeeService implements EmployeeRepository {

    @Override
    public boolean isEmployeeUsernameAlreadyExist(String employeeUsername) {
        return employeeUsername.equals("admin");
    }

    @Override
    public boolean isEmployeePasswordAlreadyExist(String employeePassword) {
        return employeePassword.equals("admin@123");
    }
}
