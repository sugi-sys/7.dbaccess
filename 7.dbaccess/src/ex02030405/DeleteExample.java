package ex02030405;

public class DeleteExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        dao.deleteById(1000);

        Employee employee = dao.load(1000);
        System.out.println(employee);
    }
}
