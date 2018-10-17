package Logic;

import DBAccess.DataMapper;
import java.util.ArrayList;
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
    
    public static List<Order> getAllOrders () throws LegohouseException {
        return DataMapper.getAllOrders();
    }
    
    public static ArrayList<Order> getAllOrdersByUser (User user) throws LegohouseException {
        return DataMapper.getOrdersByUser(user);
    }
    
    public static void createOrder (Legohouse legohouse, User user) throws LegohouseException {
        Order order = new Order(legohouse.getLength(), legohouse.getWidth(), legohouse.getHeight(), user, false);
        DataMapper.createOrder(order);
    }

    public static Legohouse createLegohouse(int length, int width, int height) {
        Legohouse house = new Legohouse(length, width, height);
        return CalcPartList.createPartList(house);
    }

}
