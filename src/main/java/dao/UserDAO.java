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

    public List<User> getAllUsers() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM user_test.users");
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age")));
            }
            resultSet.close();
            statement.close();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE user_test.users SET name = ? , age = ? WHERE id = ?"
        );
        statement.setNString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setInt(3, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM user_test.users WHERE id = '" + id + "'");
        statement.close();

    }


}
