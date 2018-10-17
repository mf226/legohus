/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Legohouse;
import Logic.LegohouseException;
import Logic.LogicFacade;
import Logic.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MadsF
 */
public class createOrder extends Command {

    public createOrder() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegohouseException {
        Legohouse legohouse = (Legohouse) request.getSession().getAttribute("legohouse");
        User user = (User) request.getSession().getAttribute("user");
        LogicFacade.createOrder(legohouse, user);
        
        return user.getRole() + "page";
    }
    
}
