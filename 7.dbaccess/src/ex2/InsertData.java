package ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String passward = "postgres";

        String sql = """
            INSERT INTO
            members
            (
                name
                ,birth_day
                ,gender
                ,color_id
            )
            VALUES
            (
                '松本 潤'
                ,'1983-08-30'
                ,'男'
                ,5
            )
            ;

                """;

        try (
            Connection con = DriverManager.getConnection(url, user, passward);
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            int num = pstmt.executeUpdate();

            System.out.println("操作した件数：" + num);
        } catch (SQLException e) {
            System.err.println("SQL : " + sql);
            e.printStackTrace();
        }
        
            //初級課題
            // INSERT INTO
            // members
            // (
            //     name
            //     ,birth_day
            //     ,gender
            //     ,color_id
            // )
            // VALUES
            // (
            //     '大野 智'
            //     ,'1980-11-26'
            //     ,'男'
            //     ,1
            // ),
            // (
            //     '櫻井 翔'
            //     ,'1982-01-25'
            //     ,'男'
            //     ,2
            // ),
            // (
            //     '相葉 雅紀'
            //     ,'1982-12-24'
            //     ,'男'
            //     ,3
            // ),
            // (
            //     '二宮 和也'
            //     ,'1983-06-17'
            //     ,'男'
            //     ,4
            // ),
            // (
            //     '松本 潤'
            //     ,'1983-08-30'
            //     ,'男'
            //     ,5
            // )
            // ;

                // UPDATE
                // members
                // SET
                // name = '山田 太郎'
                // ,birth_day = '1998-11-30'
                // ,gender = '男'
                // WHERE
                // id = 1
                // ;

                    // DELETE FROM
                    // members
                    // WHERE
                    // name = '山田 太郎'
                    // OR
                    // name = '櫻井 翔'
                    // ;

                        // DROP TABLE
                        // members
                        // ;

            //上級課題
            // DROP TABLE IF EXISTS colors;
            // CREATE TABLE colors (
            //     id      integer primary key
            //     ,name   text
            // )
            // ;

                // DROP TABLE IF EXISTS members;
                // CREATE TABLE members (
                // id          serial      primary key
                // ,name       text        not null
                // ,birth_day  date
                // ,gender     varchar(1)
                // ,color_id   integer     REFERENCES colors(id)
                // )
                // ;

                    // INSERT INTO
                    // colors
                    // (
                    //     id
                    //     ,name
                    // )
                    // VALUES
                    // (
                    //     1
                    //     ,'blue'
                    // ),
                    // (
                    //     2
                    //     ,'red'
                    // ),
                    // (
                    //     3
                    //     ,'green'
                    // ),
                    // (
                    //     4
                    //     ,'yellow'
                    // ),
                    // (
                    //     5
                    //     ,'purple'
                    // ),
                    // (
                    //     6
                    //     ,'orange'
                    // )
                    // ;

                        // INSERT INTO
                        // members
                        // (
                        //     name
                        //     ,birth_day
                        //     ,gender
                        //     ,color_id
                        // )
                        // VALUES
                        // (
                        //     '大野 智'
                        //     ,'1980-11-26'
                        //     ,'男'
                        //     ,1
                        // ),
                        // (
                        //     '櫻井 翔'
                        //     ,'1982-01-25'
                        //     ,'男'
                        //     ,2
                        // ),
                        // (
                        //     '相葉 雅紀'
                        //     ,'1982-12-24'
                        //     ,'男'
                        //     ,3
                        // ),
                        // (
                        //     '二宮 和也'
                        //     ,'1983-06-17'
                        //     ,'男'
                        //     ,4
                        // ),
                        // (
                        //     '松本 潤'
                        //     ,'1983-08-30'
                        //     ,'男'
                        //     ,5
                        // )
                        // ;

                            // UPDATE
                            // members
                            // SET
                            // name = '山田 太郎'
                            // ,birth_day = '1998-11-30'
                            // ,gender = '男'
                            // ,color_id = 6
                            // WHERE
                            // id = 1
                            // ;

                                // DELETE FROM
                                // members
                                // WHERE
                                // name = '山田 太郎'
                                // OR
                                // name = '櫻井 翔'
                                // ;

                                    // DROP TABLE
                                    // members
                                    // ;

    }
}
