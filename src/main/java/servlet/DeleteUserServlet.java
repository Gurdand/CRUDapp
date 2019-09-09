package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        if (userService.deleteUserById(Integer.parseInt(req.getParameter("id")))) {
            req.setAttribute("message", "Запись удалена!");
            resp.setStatus(200);
        } else {
            req.setAttribute("message", "Ошибка!");
            resp.setStatus(400);
        }

        getServletContext().getRequestDispatcher("/users").forward(req,resp);

    }

}
