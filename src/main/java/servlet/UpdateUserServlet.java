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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            User user = new User(Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("age")));

            System.out.println(req.getParameter("name"));

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
