/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.LogicFacade;
import Logic.LoginSampleException;
import Logic.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fen
 */
public class backdoorLogin extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String email = "robin@somewhere.com";
        String password = "batman";
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        return user.getRole() + "page";
    }

}
