package DBAccess;

import Logic.LegohouseException;
import Logic.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DataMapperTest {
//    Test date in the UsersTest table
//    INSERT INTO `UsersTest` VALUES 
//    (1,'jens@somewhere.com','jensen','customer'),
//    (2,'ken@somewhere.com','kensen','customer'),
//    (3,'robin@somewhere.com','batman','employee'),
//    (4,'someone@nowhere.com','sesam','customer');

    private static Connection testConnection;
    private static String USER = "transformer";
    private static String USERPW = "transformerpass";
    private static String DBNAME = "legohouse";
    private static String HOST = "139.59.147.129";

    @Before
    public void setUp() {
        try {
            // awoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                // Make mappers use test 
                Connector.setConnection( testConnection );
            }
//            // reset test database
//            try ( Statement stmt = testConnection.createStatement() ) {
//                stmt.execute( "drop table if exists Users" );
//                stmt.execute( "create table Users like UsersTest" );
//                stmt.execute( "insert into Users select * from UsersTest" );
//            }

        } catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull( testConnection );
    }

    @Test
    public void testLogin01() throws LegohouseException {
        // Can we log in
        User user = DataMapper.login( "customer@test.com", "123" );
        assertTrue( user != null );
    }

    @Test( expected = LegohouseException.class )
    public void testLogin02() throws LegohouseException {
        // We should get an exception if we use the wrong password
        User user = DataMapper.login( "customer@test.com", "larsen" );
    }

    @Test
    public void testLogin03() throws LegohouseException {
        // Jens is supposed to be a customer
        User user = DataMapper.login( "customer@test.com", "123" );
        assertEquals( "customer", user.getRole() );
    }

    @Test
    public void testCreateUser01() throws LegohouseException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User original = new User( "king@kong.com", "uhahvorhemmeligt", "konge" );
        DataMapper.createUser( original );
        User retrieved = DataMapper.login( "king@kong.com", "uhahvorhemmeligt" );
        assertEquals( "konge", retrieved.getRole() );
    }
}
