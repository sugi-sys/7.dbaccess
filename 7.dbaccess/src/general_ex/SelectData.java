package general_ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SelectData {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";

        String sql = """
                SELECT
                m.name AS member_name
                ,birth_day
                ,gender
                ,c.name AS color_name
                FROM
                members AS m
                LEFT JOIN
                colors AS c
                ON
                m.color_id = c.id
                ORDER BY
                m.id
                ;
                """;

        try (
            Connection con = DriverManager.getConnection(url, user, passward);
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("name     birth_day     gender     color_name" );

            while (rs.next()) {
                String name = rs.getString("member_name");
                String date  = dateFormat.format(rs.getDate("birth_day"));
                String gender = rs.getString("gender");
                String colorName = rs.getString("color_name");
                System.out.println(name + "     " + date + "     " + gender + "     " + colorName);
            }
        } catch (SQLException e) {
            System.err.println("SQL : " + sql);
            e.printStackTrace();
        }

        //初級課題
        // SELECT
        // name
        // ,birth_day
        // ,gender
        // ,color_id
        // FROM
        // members
        // ORDER BY
        // id
        // ;
        //---------
        // while (rs.next()) {
        //     String name = rs.getString("name");
        //     String date  = dateFormat.format(rs.getDate("birth_day"));
        //     String gender = rs.getString("gender");
        //     int colorId = rs.getInt("color_id");
        //     System.out.println(name + "     " + date + "     " + gender + "     " + colorId);
        // }
    }
}
