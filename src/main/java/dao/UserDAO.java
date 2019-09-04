package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO user_test.users (name, age) Values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.execute();
        statement.close();

    }

    public User getUser(int id) {
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM user_test.users");
        ResultSet resultSet = statement.getResultSet();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            userList.add(new User(resultSet.getInt("id"),resultSet.getString("name"),
                    resultSet.getInt("age")));
        }
        resultSet.close();
        statement.close();
        return userList;
    }

    public void updateUser(User user) {

    }

    public void deleteUser(Long id) {

    }


}
