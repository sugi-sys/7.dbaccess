package ex_employee_management;

import java.util.List;

public class Intermediate2 {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> employeeList = dao.findAll();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
