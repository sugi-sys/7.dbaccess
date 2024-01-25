package ex02030405;

public class LoadExercise {
    public static void main(String[] args) {
        DepartmentDao dao = new DepartmentDao();
        Department department = dao.load(1);
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());
    }
}
