package service.serviceImp;

import dao.UserDao;
import dao.daoImp.UserDaoImp;
import domain.User;
import service.UserService;
import web.tools.BeanFactory;

import java.sql.SQLException;

public class UserServiceImp implements UserService{

    @Override
    public void regist(User user) throws SQLException {
        //实现注册
        UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");
        userDao.regist(user);
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");

      return   userDao.findUserByUsername(username);

    }

    @Override
    public boolean userActive(String code) throws SQLException {

        UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");

        User user = userDao.userActive(code);

        if (null!=user){
            //根据激活码查询到一个用户
            //修改用户状态
            user.setCode(null);
            user.setState(1);
            //对数据库执行更新操作
            userDao.updata(user);
            return true;
        }else{
            return false;
        }


    }

    @Override
    public User userLogin(User user)throws SQLException {


        UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");
        User user02=userDao.userLogin(user);
        if (null==user02){
                throw new RuntimeException("密码错误");
        }else if (user02.getState()==0){

            //状态码
            throw new  RuntimeException("用户为激活");
        }else{
            return user02;
        }
    }
}
