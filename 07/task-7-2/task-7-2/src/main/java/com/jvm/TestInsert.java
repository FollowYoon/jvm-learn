package com.jvm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author qindong
 * @date 2021/11/2 9:35
 */
public class TestInsert {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&rewriteBatchedStatements=true",
                    "root", "1120");
            conn.setAutoCommit(false);
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行...");
            String inertSql = "INSERT INTO `order_info_base` (`user_id`, `address_id`, `goods_count`, `total_price`, `earn_score`) VALUES (?, ?, ?, ?, ?)";
            ptmt = conn.prepareStatement(inertSql);
            for (int i = 0; i < 1000000; i++) {
                ptmt.setInt(1, 1);
                ptmt.setInt(2, 1);
                int count = (int)(Math.random()*10);
                ptmt.setInt(3, count);
                ptmt.setInt(4, count*3000);
                ptmt.setInt(5, 10);
                ptmt.addBatch();
            }
            ptmt.executeBatch();
            conn.commit();
            System.out.println("执行完成,耗时:" + (System.currentTimeMillis() - startTime) +"ms");
            // 只使用PreparedStatement，循环执行ptmt.executeUpdate()时：
            // 执行完成,耗时:102954ms
            // *
            // 使用PreparedStatement，循环执行addBatch()，再一次性提交executeBatch()时：
            // 执行完成,耗时:13712ms
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
