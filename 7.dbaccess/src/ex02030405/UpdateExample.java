package ex02030405;

public class UpdateExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();

        System.out.println("更新前-------");
        Employee employee = dao.load(1000);
        System.out.println("id = " + employee.getId());
        System.out.println("name = " + employee.getName());
        System.out.println("age = " + employee.getAge());
        System.out.println("gender = " + employee.getGender());
        System.out.println("dep_id = " + employee.getDepartmentId());

        employee.setName("伊賀");
        employee.setAge(18);
        employee.setGender("男");
        employee.setDepartmentId(4);
        dao.update(employee);

        System.out.println("更新後-------");
        employee = dao.load(1000);
        System.out.println("id = " + employee.getId());
        System.out.println("name = " + employee.getName());
        System.out.println("age = " + employee.getAge());
        System.out.println("gender = " + employee.getGender());
        System.out.println("dep_id = " + employee.getDepartmentId());

    }
}
