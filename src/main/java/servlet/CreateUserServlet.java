package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String message = "Ошибка! Юзер не добавлен!";

        try {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setAge(Integer.parseInt(req.getParameter("age")));

            if (userService.createUser(user)) {
                message = "Новый юзер " + user.getName() + " добавлен!";
                resp.setStatus(200);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
        }

        req.setAttribute("message", message);

        getServletContext().getRequestDispatcher("/users").forward(req,resp);
    }
}
