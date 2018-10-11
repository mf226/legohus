package Logic;

import DBAccess.DataMapper;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LegohouseException {
        return DataMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LegohouseException {
        User user = new User(email, password, "customer");
        DataMapper.createUser( user );
        return user;
    }
    
    public static List<Order> getAllOrders () {
        return DataMapper.getAllOrders();
    }

}
