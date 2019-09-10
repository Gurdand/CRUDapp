package dao;

import exception.ApplicationException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void createUser(User user) throws SQLException, ApplicationException;

    List<User> getAllUsers() throws ApplicationException;

    void updateUser(User user) throws SQLException, ApplicationException;

    void deleteUser(int id) throws SQLException, ApplicationException;
}
