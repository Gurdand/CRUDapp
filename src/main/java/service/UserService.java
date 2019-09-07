package service;

import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class UserService {

    private static UserDaoFactory factory = UserDaoFactory.getFactory();

    public boolean createUser(User user) {
        try {
            factory.getUserDAO().createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return factory.getUserDAO().getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserById(int id) {
        try {
            factory.getUserDAO().deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            factory.getUserDAO().updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
