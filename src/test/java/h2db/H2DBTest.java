package h2db;

import static org.junit.jupiter.api.Assertions.*;

import org.h2.Driver;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2DBTest {

    @Test
    public void testRunningH2DB() throws Exception{
        Driver.load();
        try(Connection con = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            Statement st = con.createStatement();
            st.execute("create table test (id int primary key,name varchar)");
            st.execute("insert into test values (1, 'hoge')");
            ResultSet rs = st.executeQuery("select * from test");
            rs.next();
            assertEquals(1, rs.getInt("id"));
            assertEquals("hoge", rs.getString("name"));
            assertFalse(rs.next());
            st.close();
        }
    }
}
