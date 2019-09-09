package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class UserService implements Service {

    private static UserService userService;

    private UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(UserDaoFactory.getUserDAO());
        }
        return userService;
    }

    public boolean createUser(User user) {
        try {
            userDAO.createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserById(int id) {
        try {
            userDAO.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            userDAO.updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
