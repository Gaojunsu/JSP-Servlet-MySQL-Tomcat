package dao;

import domain.User;

import java.sql.SQLException;

public interface UserDao {
    void regist(User user) throws SQLException;

    User findUserByUsername(String username) throws SQLException;

    User userActive(String code) throws SQLException;

    void updata(User user) throws SQLException;

    User userLogin(User user)throws SQLException;
}

