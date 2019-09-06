package service;

import dao.UserDAO;
import model.User;
import util.HibernateSessionFactory;

import java.util.List;

public class UserService {

    public boolean createUser(User user) {
        try {
            new UserDAO(HibernateSessionFactory.getSession()).createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return new UserDAO(HibernateSessionFactory.getSession()).getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserById(int id) {
        try {
            new UserDAO(HibernateSessionFactory.getSession()).deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            new UserDAO(HibernateSessionFactory.getSession()).updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
