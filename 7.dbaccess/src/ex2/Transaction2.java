package ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

        //意図的にロールバックさせてみる


public class Transaction2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";


        // DELETEを1回行い、その後故意にSQLExceptionを発生させてロールバックを強制する
        String sql = """
                DELETE FROM
                members
                WHERE
                name = '松本 潤'
                ;
                """;

        // Connectionインターフェース側でトランザクションを制御する
        try (
            Connection con = DriverManager.getConnection(url, user, passward);
        ) {
            con.setAutoCommit(false);       // 自動的にコミットするのを防ぐ

            //setAutoCommitをfalseにしなければならないため、二重try　＆
            //例外発生時にロールバックさせるためにはスコープの関係上二重tryの必要性あり
            try (
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                int num = pstmt.executeUpdate();
                System.out.println("操作した件数：" + num);

                if (num == 1) {
                    throw new SQLException();
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.err.println("ロールバックしました。");
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
