package ex02030405;

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
                id
                ,name
                ,age
                ,gender
                ,department_id
                FROM
                employees
                WHERE
                id = ?
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                return employee;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            // catch内で戻り値が指定されていないとエラーになるため、かわりにRuntimeExceptionをthrow
            throw new RuntimeException("エラー",e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public List<Employee> findByDepartmentId(int departmentId) {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                id
                ,name
                ,age
                ,gender
                ,department_id
                FROM
                employees
                WHERE
                department_id = ?
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, departmentId);

            ResultSet rs = pstmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employeeList.add(employee);
            }

            return employeeList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("エラー",e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public int insert(Employee employee) {
        Connection con = DBManager.createConnection();

        String sql = """
                INSERT INTO
                employees
                (id, name, age, gender, department_id)
                VALUES
                ( ?,    ?,   ?,      ?,             ?)
                ;
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setInt(3, employee.getAge());
            pstmt.setString(4, employee.getGender());
            pstmt.setInt(5, employee.getDepartmentId());

            int affected = pstmt.executeUpdate();
            return affected;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("エラー",e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public int update(Employee employee) {
        Connection con = DBManager.createConnection();

        String sql = """
                UPDATE
                employees
                SET
                name = ?
                ,age = ?
                ,gender = ?
                ,department_id = ?
                WHERE
                id = ?
                ;
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getGender());
            pstmt.setInt(4, employee.getDepartmentId());
            pstmt.setInt(5, employee.getId());

            int affected = pstmt.executeUpdate();
            return affected;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("エラー",e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public int deleteById(int id) {
        Connection con = DBManager.createConnection();

        String sql = """
                DELETE FROM
                employees
                WHERE
                id = ?
                ;
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int affected = pstmt.executeUpdate();
            return affected;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("エラー",e);
        } finally {
            DBManager.closeConnection(con);
        }
    }
}
