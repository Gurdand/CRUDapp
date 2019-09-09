package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public interface Service {

    boolean createUser(User user);

    List<User> getAllUsers();

    boolean deleteUserById(int id);

    boolean updateUser(User user);
}
