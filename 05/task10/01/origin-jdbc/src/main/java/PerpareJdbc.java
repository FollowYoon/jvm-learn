import java.sql.*;

/**
 * @author qindong
 * @date 2021/10/17 17:32
 */
public class PerpareJdbc {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC",
                    "root", "1120");
            conn.setAutoCommit(false);
            String inertSql = "INSERT INTO `user` (`id`, `name`) VALUES (?, ?);";
            ptmt = conn.prepareStatement(inertSql);
            for (int i = 2; i < 10; i++) {
                ptmt.setInt(1, i);
                ptmt.setString(2, "zhangsan" + i);
                ptmt.executeUpdate();
            }
            conn.commit();
            System.out.println("执行完成");

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
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
