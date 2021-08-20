package repository;

public interface EmployeeRepository {
    public boolean isEmployeeUsernameAlreadyExist(String employeeUsername);
    public boolean isEmployeePasswordAlreadyExist(String employeePassword);
}
