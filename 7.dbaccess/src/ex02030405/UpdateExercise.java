package ex02030405;

public class UpdateExercise {
    public static void main(String[] args) {
        DepartmentDao dao = new DepartmentDao();

        System.out.println("更新前-------");
        Department department = dao.load(1000);
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());

        department.setName("IT事業部");
        dao.update(department);

        System.out.println("更新後-------");
        department = dao.load(1000);
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());
    }
}
