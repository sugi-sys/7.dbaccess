package ex_member_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public Student load(int id) {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                s.id AS student_id
                ,s.name AS student_name
                ,age
                ,h.id AS hobby_id
                ,h.name AS hobby_name
                ,h.student_id AS student_id_hobby
                FROM
                students AS s
                LEFT JOIN
                hobbies AS h
                ON
                s.id = h.student_id
                WHERE
                s.id = ?
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            List<Hobby> hobbyList = new ArrayList<>();
            Student student = null;
            Hobby hobby = null;

            while (rs.next()) {
                if (student == null) {
                    student = new Student();
                    student.setId(rs.getInt("student_id"));
                    student.setName(rs.getString("student_name"));
                    student.setAge(rs.getInt("age"));
                }

                hobby = new Hobby();
                hobby.setId(rs.getInt("hobby_id"));
                hobby.setName(rs.getString("hobby_name"));
                hobby.setStudentId(rs.getInt("student_id_hobby"));
                hobbyList.add(hobby);
            }
            if (student != null) {
                student.setHobbyList(hobbyList);
                return student;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public List<Student> findAll() {
        Connection con = DBManager.createConnection();

        String sql = """
                SELECT
                s.id AS student_id
                ,s.name AS student_name
                ,age
                ,h.id AS hobby_id
                ,h.name AS hobby_name
                ,h.student_id AS student_id_hobby
                FROM
                students AS s
                LEFT JOIN
                hobbies AS h
                ON
                s.id = h.student_id
                ORDER BY
                s.id
                ;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            List<Student> studentList = new ArrayList<>();
            List<Hobby> hobbyList = new ArrayList<>();
            Integer preStudentId = null;
            Student student = null;
            Hobby hobby = null;

            while (rs.next()) {       
                if (preStudentId != rs.getInt("student_id")) {
                    if (student != null) {
                        student.setHobbyList(hobbyList);
                        studentList.add(student);
                        hobbyList = new ArrayList<>();
                    }
                    student = new Student();
                    student.setId(rs.getInt("student_id"));
                    student.setName(rs.getString("student_name"));
                    student.setAge(rs.getInt("age"));
                    preStudentId = rs.getInt("student_id");
                }
                hobby = new Hobby();
                hobby.setId(rs.getInt("hobby_id"));
                hobby.setName(rs.getString("hobby_name"));
                hobby.setStudentId(rs.getInt("student_id_hobby"));
                hobbyList.add(hobby);
            }
            // 最終行処理
            if (student != null) {
                student.setHobbyList(hobbyList);
                studentList.add(student);
            }

            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }
    }


    public int insert(Student student) {
        Connection con = DBManager.createConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("""
            INSERT INTO
            students
            (name, age)
            VALUES
            (   ?,   ?)
            ;
            """);                   // 「;」を必ずアペンド！
               
        try {
            sql.append("""
                INSERT INTO
                hobbies
                (name, student_id)
                VALUES
                """);                   // 場合によってはnullチェック等が必要

            for (int i = 0; i < student.getHobbyList().size(); i++) {
                sql.append(",(   ?,   ?)");
            }
            
            sql.append(";");

            PreparedStatement pstmt = con.prepareStatement(sql.toString());

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());

            int j = 3;
            for (int i = 0; i < student.getHobbyList().size(); i++ ) {
                pstmt.setString(j, student.getHobbyList().get(i).getName());
                j++;
                pstmt.setInt(j, student.getHobbyList().get(i).getStudentId());
                j++;
            }


            ResultSet rs = pstmt.executeQuery();



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(con);
        }

    }
}
