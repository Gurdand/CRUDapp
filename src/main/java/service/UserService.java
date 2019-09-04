package service;

import dao.UserDAO;
import model.User;
import util.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public boolean createUser(User user) {
        try {
            new UserDAO(new DBConnection().getConnection()).createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        return new UserDAO(new DBConnection().getConnection()).getAllUsers();
    }

    public boolean deleteUserById(int id) {
        try {
            new UserDAO(new DBConnection().getConnection()).deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            new UserDAO(new DBConnection().getConnection()).updateUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
