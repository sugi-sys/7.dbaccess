package ex_employee_management;

public class Intermediate1 {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = dao.load(1);
        System.out.println(employee);
    }
}
