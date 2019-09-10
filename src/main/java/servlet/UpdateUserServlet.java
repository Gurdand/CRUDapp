package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String message = "Ошибка! Невозможно обновить данные!";

        try {
            User user = new User(Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("age")));

            if (userService.updateUser(user)) {
                message = "Данные обновлены!";
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
        }

        req.setAttribute("message", message);

        getServletContext().getRequestDispatcher("/users").forward(req,resp);

    }
}
