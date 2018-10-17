package DBAccess;

import Logic.LegohouseException;
import Logic.Order;
import Logic.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of UserMapper is to...
 *
 * @author kasper
 */
public class DataMapper {


    public static void createUser(User user) throws LegohouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            ids.next();
//            int id = ids.getInt( 1 );
//            user.setId( id );
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LegohouseException(ex.getMessage());
        }
    }

    public static void createOrder(Order order) throws LegohouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders (email_FK, length, width, height, shipped) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getUser().getEmail());
            ps.setInt(2, order.getLength());
            ps.setInt(3, order.getWidth());
            ps.setInt(4, order.getHeight());
            ps.setBoolean(5, order.isShipped());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setOrder_id(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LegohouseException(ex.getMessage());
        }
    }

    public static User login(String email, String password) throws LegohouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT role FROM users " + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                //int id = rs.getInt( "id" );
                User user = new User(email, password, role);
                //user.setId( id );
                return user;
            } else {
                throw new LegohouseException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegohouseException(ex.getMessage());
        }
    }

    public static List<Order> getAllOrders() throws LegohouseException {
        ArrayList<Order> orders = new ArrayList();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT order_id, email_FK, length, width, height, "
                    + "shipped, `password`, role FROM legohouse.orders JOIN legohouse.users"
                    + " where orders.email_FK = users.email;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                String email = rs.getString("email_FK");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean shipped = rs.getBoolean("shipped");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User user = new User(email, password, role);
                orders.add(new Order(orderID, length, width, height, user, shipped));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegohouseException(ex.getMessage());
        }
        return orders;
    }
    
    public static ArrayList<Order> getOrdersByUser(User user) throws LegohouseException {
        ArrayList<Order> orders = new ArrayList();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT order_id, length, width, height, shipped "
                    + "FROM legohouse.orders WHERE email_FK = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean shipped = rs.getBoolean("shipped");
                orders.add(new Order(orderID, length, width, height, user, shipped));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegohouseException(ex.getMessage());
        }
        return orders;
    }

}
