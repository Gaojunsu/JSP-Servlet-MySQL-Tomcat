package service;

import domain.User;

import java.sql.SQLException;

public interface UserService {
    void regist(User user) throws SQLException;

    User findUserByUsername(String username) throws SQLException;

    boolean userActive(String code) throws SQLException;

    User userLogin(User user)throws SQLException;
}
