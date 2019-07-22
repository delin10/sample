package nil.ed;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Unit test for simple Application.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true","root","123456");
        int majorVersion=connection.getMetaData().getDatabaseMajorVersion();
        System.out.println(majorVersion);
    }
}
