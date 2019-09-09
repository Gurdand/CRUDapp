package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class UserService implements Service {

    private static final UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();

    //private UserDAO userDAO;

//    public UserService() {
//            userDAO = userDaoFactory.getUserDAO();
//    }

    public boolean createUser(User user) {
        try {
            userDaoFactory.getUserDAO().createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDaoFactory.getUserDAO().getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserById(int id) {
        try {
            userDaoFactory.getUserDAO().deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            userDaoFactory.getUserDAO().updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
