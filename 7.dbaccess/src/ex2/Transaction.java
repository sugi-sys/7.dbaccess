package ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";

        String sql = """
                UPDATE
                members
                SET
                name = '山田 太郎'
                ,birth_day = '1998-11-30'
                ,gender = '男'
                ,color_id = 6
                WHERE
                id = 1
                ;
                """;
        // トランザクション処理を入れる場合、SQLでBEGIN/COMMITを直打ちしても実装可能だが、
        // 冗長なコードになるため、Connectionインターフェース側で制御したほうが無難
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
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
