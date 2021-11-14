import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author qindong
 * @date 2021/11/12 17:23
 */
public class AtomikosXADemo {

    public static void main(String[] args) throws Exception{
        String fileName = "D:\\learn\\task\\w1\\08\\task-8-6\\task-8-6\\src\\main\\resources\\sharding.yml";
        File yamlFile = new File(fileName);
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);

        TransactionTypeHolder.set(TransactionType.XA);
        Connection conn = dataSource.getConnection();

        String sql = "insert into t_order (user_id, order_id) VALUES (?, ?);";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 1; i < 6; i++) {
                statement.setLong(1, i);
                statement.setLong(2, i);
                statement.executeUpdate();
            }
            conn.commit();
        }
        conn.commit();
        System.out.println("XA 1 insert successful");

        System.out.println("XA 2 Start");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 1; i < 6; i++) {
                statement.setLong(1, i+5);
                statement.setLong(2, i+5);
                statement.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("XA 2 insert failed");
            conn.rollback();
        } finally {
            conn.close();
        }

    }

}
