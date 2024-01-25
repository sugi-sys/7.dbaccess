package ex02030405;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private final static String URL = "jdbc:postgresql://localhost:5432/student";
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "postgres";

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
            // 戻り値の型指定を行っているため、catch内でも何かを返す必要がある。
            // SQLExceptionを返してしまうと、メソッド定義時にthrows指定する必要があり、
            // かつ呼び出し元でもハンドリング処理を行う必要性がでてくるため、強制的に非検査例外としている。
            throw new RuntimeException("DBの接続に失敗しました",e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DBの切断に失敗しました",e);
        }
    }
}
