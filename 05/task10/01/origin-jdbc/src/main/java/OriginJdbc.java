import java.sql.*;

/**
 * @author qindong
 * @date 2021/10/17 17:32
 */
public class OriginJdbc {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC",
                    "root", "1120");

            stmt = conn.createStatement();

            String inertSql = "INSERT INTO `user` (`id`, `name`) VALUES ('1', 'ZHANGSAN');";
            String querySql = "select * from user";
            String updateSql = "update user set name='333' where id = 1";
            String deleteSql = "delete from user where id =1";

            //int affectLine = stmt.executeUpdate(querySql);
            //System.out.println("影响的行数:" + affectLine);

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
