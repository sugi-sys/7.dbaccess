package ex_employee_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public Employee load(int id) {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                e.id
                ,e.name AS name
                ,age
                ,gender
                ,department_id
                ,d.name AS department
                FROM
                employees AS e
                LEFT JOIN
                departments AS d
                ON
                e.department_id = d.id
                WHERE
                e.id = ?
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department");

                Employee employee = new Employee();
                employee.setId(employeeId);
                employee.setName(name);
                employee.setAge(age);
                employee.setGender(gender);
                employee.setDepartmentId(departmentId);

                Department department = new Department();
                department.setId(departmentId);
                department.setName(departmentName);
                
                employee.setDepartment(department);
                return employee;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }

    }


    public List<Employee> findAll() {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                e.id
                ,e.name AS name
                ,age
                ,gender
                ,department_id
                ,d.name AS department
                FROM
                employees AS e
                LEFT JOIN
                departments AS d
                ON
                e.department_id = d.id
                ORDER BY
                e.id
                ;
                """;
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int employeeId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department");

                Employee employee = new Employee();
                employee.setId(employeeId);
                employee.setName(name);
                employee.setAge(age);
                employee.setGender(gender);
                employee.setDepartmentId(departmentId);

                Department department = new Department();
                department.setId(departmentId);
                department.setName(departmentName);

                employee.setDepartment(department);

                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }
    } 


    public List<Employee> findByDepartmentId(int id) {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                e.id
                ,e.name AS name
                ,age
                ,gender
                ,department_id
                ,d.name AS department
                FROM
                employees AS e
                LEFT JOIN
                departments AS d
                ON
                e.department_id = d.id
                WHERE
                e.department_id = ?
                ORDER BY
                e.id
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int employeeId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department");

                Employee employee = new Employee();
                employee.setId(employeeId);
                employee.setName(name);
                employee.setAge(age);
                employee.setGender(gender);
                employee.setDepartmentId(departmentId);

                Department department = new Department();
                department.setId(departmentId);
                department.setName(departmentName);

                employee.setDepartment(department);

                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }
    }
}
