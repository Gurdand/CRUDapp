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

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String message = "Ошибка! Юзер не добавлен!";

        try {
            User user = new User();
            user.setName(new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            user.setAge(Integer.parseInt(req.getParameter("age")));

            if (new UserService().createUser(user)) {
                message = "Новый юзер " + user.getName() + " добавлен!";

                resp.setStatus(200);
            }

        } catch (Exception e) {
            message = "Ошибка! Юзер не добавлен!";
            resp.setStatus(400);
        }

        req.setAttribute("message", message);

        getServletContext().getRequestDispatcher("/users").forward(req,resp);
    }
}
