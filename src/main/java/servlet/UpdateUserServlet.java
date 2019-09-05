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

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            User user = new User(Integer.parseInt(req.getParameter("id")),
                    new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) ,
                    Integer.parseInt(req.getParameter("age")));

            if (new UserService().updateUser(user)) {
                req.setAttribute("message", "Данные обновлены!");
                resp.setStatus(200);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Ошибка! Невозможно обновить данные!");
            resp.setStatus(400);
        }

        getServletContext().getRequestDispatcher("/users").forward(req,resp);

    }
}
