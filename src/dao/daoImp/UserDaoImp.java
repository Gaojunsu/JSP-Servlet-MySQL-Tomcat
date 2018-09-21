package dao.daoImp;

import dao.UserDao;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import web.tools.JDBCUtils;

import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    @Override
    public void regist(User user) throws SQLException {


        String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUid(), user.getUsername(),
                user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getBirthday(), user.getSex(),
                user.getState(), user.getCode()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, params);

    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        String sql="select * from user where username=?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<User>(User.class),username);
    }

    @Override
    public User userActive(String code) throws SQLException {


        String sql="select * from user where code=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return   queryRunner.query(sql, new BeanHandler<User>(User.class), code);


    }





    @Override
    public void updata(User user) throws SQLException {
            String sql="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
            Object[] parament={user.getUsername(),user.getPassword(),
            user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
            user.getSex(),user.getState(),user.getCode(),user.getUid()};
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            queryRunner.update(sql,parament);


    }

    @Override
    public User userLogin(User user) throws SQLException {
        String sql="select * from user where username=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return   queryRunner.query(sql,new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
    }
}
