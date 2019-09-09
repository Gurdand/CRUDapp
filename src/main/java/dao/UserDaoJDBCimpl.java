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

        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new SQLException("Ошибка транзакции!");
        }

        connection.commit();
    }

    @Override
    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM user_test.users");
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getInt("age")));
            }
            resultSet.close();
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {

        connection.setAutoCommit(false);

        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE user_test.users SET name = ? , age = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setInt(3, user.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new SQLException("Ошибка транзакции!");
        }

        connection.commit();
    }

    @Override
    public void deleteUser(int id) throws SQLException {

        connection.setAutoCommit(false);

        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user_test.users WHERE id = '" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new SQLException("Ошибка транзакции!");
        }

        connection.commit();
    }
}
