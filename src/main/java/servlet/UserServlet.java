package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("message", req.getAttribute("message"));

        try {
            List<User> users = new UserService().getAllUsers();

            if (users == null) {
                throw new SQLException();
            }

            req.setAttribute("users", users);
            resp.setStatus(200);

        } catch (SQLException e) {
            req.setAttribute("message", "Упс! Что то пошло не так! =(");
            resp.setStatus(400);
        }

        getServletContext().getRequestDispatcher("/templates/users.jsp").forward(req,resp);

    }
}
