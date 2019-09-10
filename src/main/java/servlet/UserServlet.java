package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

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
            List<User> users = userService.getAllUsers();

            req.setAttribute("users", users);
            resp.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Упс! Что то пошло не так! =(");
            resp.setStatus(400);
        }

        getServletContext().getRequestDispatcher("/users.jsp").forward(req,resp);

    }
}
