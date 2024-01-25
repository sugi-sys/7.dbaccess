package ex_employee_management;

import java.util.List;

public class Intermediate3 {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> employeeList  = dao.findByDepartmentId(2);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
