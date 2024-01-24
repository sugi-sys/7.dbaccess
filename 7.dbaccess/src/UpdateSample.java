import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            con = DriverManager.getConnection(url, user, passward);
            
            sql = """
                    INSERT INTO
                    employees
                    (name
                    ,age)
                    VALUES
                    ('テスト太郎'
                    ,19
                    )
                    ;
                    """;

            pstmt = con.prepareStatement(sql);

            int numOfUpdate = pstmt.executeUpdate();

            System.out.println(numOfUpdate + "件のデータを操作しました。");
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}