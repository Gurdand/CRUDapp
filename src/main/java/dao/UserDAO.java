package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void createUser(User user) throws SQLException;

    List<User> getAllUsers();

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;
}
