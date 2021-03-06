package Logic;

import DBAccess.DataMapper;
import java.util.List;

/**
 * The purpose of User is to...
 * @author kasper
 */
public class User {

    public User( String email, String password, String role ) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private String email;
    private String password; // Should be hashed and secured
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }
    
    public List<Order> getOrders() throws LegohouseException {
        return DataMapper.getOrdersByUser(this);
    }

    @Override
    public String toString() {
        return email;
    }

}
