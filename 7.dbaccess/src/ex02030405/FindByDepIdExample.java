package ex02030405;

import java.util.List;

public class FindByDepIdExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> employeeList = dao.findByDepartmentId(2);

        for (Employee employee : employeeList) {
            System.out.println("name = " + employee.getName());
        }
    }
}
