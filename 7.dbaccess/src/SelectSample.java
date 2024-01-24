import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSample {
    public static void main(String[] args) {

        //何回か書いたほうが良さそう

        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";

        // 使用する変数の宣言
        Connection con = null;              //接続情報を保持するための変数（クラス）
        PreparedStatement pstmt = null;     //SQL文を保持するための変数（クラス）
        ResultSet rs = null;                //検索結果を保持するための変数（クラス）
        String sql = null;                  //SQL文を保持するための変数（文字列）

        try {
            //データベースへの接続(static)
            con = DriverManager.getConnection(url, user, passward);

            //SQL文の作成
            sql = """
                SELECT
                id
                ,name
                ,age
                FROM
                employees
                ORDER BY
                age
                ,id;
            """;

            //PreparedStatementオブジェクトの取得（実行準備）
            pstmt = con.prepareStatement(sql);

            //SQL文の実行
            rs = pstmt.executeQuery();

            //検索結果の取得
            while (rs.next()) {
                //検索結果からデータを取得
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                //取得したデータを出力
                System.out.println("id:" + id);
                System.out.println(" name:" + name);
                System.out.println(" age:" + age);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("SQL関連の例外が発生しました。");
            System.err.println("発行したSQLは「" + sql + "」");
            e.printStackTrace();
        } finally {
            //接続を切断
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
}
