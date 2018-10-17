/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.HashMap;

/**
 *
 * @author MadsF
 */
public class Side {
    private HashMap<String, Integer> bricks;

    public Side() {
        bricks = new HashMap();
    }

    public void setBricks(String sizeOfBrick, Integer amount) {
        bricks.put(sizeOfBrick, amount);
    }

    public HashMap<String, Integer> getBricks() {
        return bricks;
    }

    @Override
    public String toString() {
        return "" + bricks;
    }
    
    
    
}
