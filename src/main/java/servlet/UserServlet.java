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

    private String message = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("message", message);

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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");


        if (req.getPathInfo().contains("add")) {

            //String message = "Ошибка! Юзер не добавлен!";

            try {
                User user = new User();
                user.setName(new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

                System.out.println(req.getParameter("name"));

                user.setAge(Integer.parseInt(req.getParameter("age")));

                if (new UserService().createUser(user)) {
                    //message = "Новый юзер " + user.getName() + " добавлен!";
                    message = "Новый юзер " + user.getName() + " добавлен!";

                    resp.setStatus(200);
                }

            } catch (Exception e) {
                message = "Ошибка! Юзер не добавлен!";
                resp.setStatus(400);
            }

            resp.sendRedirect("/users");
        }


        if (req.getPathInfo().contains("delete")) {

            if (new UserService().deleteUserById(Integer.parseInt(req.getParameter("id")))) {
                //req.setAttribute("message", "Запись удалена!");
                message = "Запись удалена!";
                resp.setStatus(200);
            } else {
                //req.setAttribute("message", "Ошибка!");
                message = "Ошибка!";
                resp.setStatus(400);
            }

            resp.sendRedirect("/users");
        }


        if (req.getPathInfo().contains("update")) {

            try {
                User user = new User(Integer.parseInt(req.getParameter("id")),
                        new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) ,
                        Integer.parseInt(req.getParameter("age")));

                System.out.println(req.getParameter("name"));

                if (new UserService().updateUser(user)) {
                    //req.setAttribute("message", "Данные обнвлены!");
                    message = "Данные обнвлены!";
                    resp.setStatus(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //req.setAttribute("message", "Ошибка! Невозможно обновить данные!");
                message = "Ошибка! Невозможно обновить данные!";
                resp.setStatus(400);
            }

            resp.sendRedirect("/users");

        }



    }
}
