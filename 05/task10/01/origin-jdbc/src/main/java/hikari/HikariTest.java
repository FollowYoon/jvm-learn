package hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author qindong
 * @date 2021/10/17 18:25
 */
public class HikariTest {

    public static void main(String[] args) {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("1120");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        conf.setMaximumPoolSize(20);
        conf.setIdleTimeout(60000);
        conf.setConnectionTimeout(20000);
        HikariDataSource ds = new HikariDataSource(conf);

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            String querySql = "select * from user";

            ResultSet result = stmt.executeQuery(querySql);
            if(result.next()){
                System.out.println("查询结果:" + result.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
