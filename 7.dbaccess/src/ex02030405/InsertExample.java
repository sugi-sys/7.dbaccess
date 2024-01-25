package ex02030405;

public class InsertExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = new Employee();
        employee.setId(1000);
        employee.setName("テスト太郎");
        employee.setAge(22);
        employee.setGender("男");
        employee.setDepartmentId(2);

        dao.insert(employee);

        employee = dao.load(1000);
        System.out.println("id = " + employee.getId());
        System.out.println("name = " + employee.getName());
        System.out.println("age = " + employee.getAge());
        System.out.println("gender = " + employee.getGender());
        System.out.println("dep_id = " + employee.getDepartmentId());

    }
}
