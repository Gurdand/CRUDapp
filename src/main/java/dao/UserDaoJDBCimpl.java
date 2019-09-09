package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDAO {

    private Connection connection;

    UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO user_test.users (name, age) Values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        try {
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            statement.cancel();
            statement.close();
            throw new SQLException("Ошибка транзакции!");
        }
    }

    @Override
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

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE user_test.users SET name = ? , age = ? WHERE id = ?"
        );
        statement.setNString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setInt(3, user.getId());
        try {
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            statement.cancel();
            statement.close();
            throw new SQLException("Ошибка транзакции!");
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("DELETE FROM user_test.users WHERE id = '" + id + "'");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            statement.cancel();
            statement.close();
            throw new SQLException("Ошибка транзакции!");
        }
    }
}
