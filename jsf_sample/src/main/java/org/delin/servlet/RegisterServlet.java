package org.delin.servlet;

import org.delin.entities.UserEntity;
import org.delin.service.UserService;
import org.delin.service.impl.UserServiceImpl;
import org.delin.util.web.RequestParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user=(UserEntity) new RequestParser().parseBody(request,UserEntity.class);
        if (userService.addUser(user)){
            request.getRequestDispatcher("register_success.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("register_fail.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
