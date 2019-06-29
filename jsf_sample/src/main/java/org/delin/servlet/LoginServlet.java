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

@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private RequestParser parser = new RequestParser();
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = (UserEntity) parser.parseBody(request, UserEntity.class);
        System.out.println(user);
        if (userService.check(user)) {
            response.getWriter().append(user.getName()).append(" login success!login count:").append(userService.getLoginCount() + "");
        } else {
            response.getWriter().print("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
