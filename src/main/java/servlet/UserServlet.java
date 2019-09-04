package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<User> users = new UserService().getAllUsers();
            req.setAttribute("users", users);
            resp.setStatus(200);

        } catch (SQLException e) {
            req.setAttribute("message", "Упс! Что то пошло не так! =(");
            resp.setStatus(400);
        }
//        users.add(new User(1, "Bill", 28));
//        users.add(new User(2, "John", 35));
//        users.add(new User(3, "Elly", 23));

        getServletContext().getRequestDispatcher("/templates/users.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getPathInfo().contains("add")) {

            String message = "Ошибка! Юзер не добавлен!";

            try {
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setAge(Integer.parseInt(req.getParameter("age")));

                if (new UserService().createUser(user)) {
                    message = "Новый юзер " + user.getName() + " добавлен!";

                    resp.setStatus(200);
                }

            } catch (Exception e) {
                resp.setStatus(400);
            }

            req.setAttribute("message", message);

            doGet(req,resp);
        }

        if (req.getPathInfo().contains("delete")) {
            req.setAttribute("message", req.getParameter("id"));

            if (new UserService().deleteUserById(Integer.parseInt(req.getParameter("id")))) {
                req.setAttribute("message", "Запись удалена!");
                resp.setStatus(200);
            } else {
                req.setAttribute("message", "Ошибка!");
                resp.setStatus(400);
            }

            doGet(req,resp);
        }

        if (req.getPathInfo().contains("update")) {

        }



    }
}
