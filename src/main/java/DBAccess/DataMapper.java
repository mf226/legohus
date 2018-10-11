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

    private final String GET_USER_BY_USERNAME = "SELECT username, password, balance FROM CupcakeShop.user WHERE username = ?";
    private final String GET_ALL_USERS = "SELECT username, password, balance FROM CupcakeShop.user;";
    private final String GET_BOTTOM = "SELECT bottomName, price FROM CupcakeShop.bottom WHERE bottomName = ?";
    private final String GET_BOTTOMS = "SELECT bottomName, price FROM CupcakeShop.bottom;";
    private final String GET_TOPPING = "SELECT toppingName, price FROM CupcakeShop.topping Where toppingName = ?";
    private final String GET_TOPPINGS = "SELECT toppingName, price FROM CupcakeShop.topping;";
    private final String GET_NEXT_ORDERID = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES\n" + "WHERE table_name = 'order'";
    private final String GET_ALL_ORDERS = "SELECT orderID, invoice, price, orderDate, user FROM `order`;";
    private final String GET_ALL_ORDERS_BY_USERNAME = "SELECT orderID, invoice, price, orderDate, user FROM `order` WHERE user = ?";

    private final String ADD_NEW_USER = "INSERT INTO user(username,password,balance) VALUES (?,?,?)";
    private final String ADD_TO_CUPCAKEDETAILS = "INSERT INTO cupcakeDetails(orderID, qty, topping, bottom) VALUES(?,?,?,?)";
    private final String ADD_BOTTOM = "INSERT INTO bottom(bottomName, price) VALUES (?,?)";
    private final String ADD_TOPPING = "INSERT INTO topping(toppingName, price) VALUES (?,?)";
    private final String ADD_ORDER = "INSERT INTO `order`(price, user) VALUES (?,?)";

    private final String UPDATE_USER = "UPDATE `CupcakeShop`.`user` SET `balance`=? WHERE `username`= ?";

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

    public static User login(String email, String password) throws LegohouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT role FROM users "
                    + "WHERE email=? AND password=?";
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
            String SQL = "SELECT order_id, email_FK, length, width, height, shipped, `password`, role FROM legohouse.orders JOIN legohouse.users where orders.email_FK = users.email;";
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

}
