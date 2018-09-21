package web.servlet;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import service.UserService;
import service.serviceImp.UserServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;
import web.tools.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;



public class UserServlet extends BaseServlet {




    //注册页
    public String registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "jsp/register.jsp";
    }
    //注册
    public String userRegist(HttpServletRequest req, HttpServletResponse resp)  {
        //接收表单数据

               /*     User user = new User();

                Map<String, String[]> map = req.getParameterMap();
            Set<String> keySet = map.keySet(); Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()){
                    String next = iterator.next();  System.out.println(next);
                    String[] strings = map.get(next);
                    for (String s:strings) {  System.out.println(s);  }
                }*/
        User user = Utils.populate(User.class, req.getParameterMap());
        user.setState(0);


        user.setUid(UUID.randomUUID().toString());
        user.setCode(UUID.randomUUID().toString());


        //调用业务层注册功能
        UserService userService = (UserService) BeanFactory.createObject("UserService");

        try {
            userService.regist(user);
            //注册成功，想用户发送邮件，跳转
            req.setAttribute("msg", "用户注册成功,请激活!");
        } catch (SQLException e) {
            e.printStackTrace();
            //注册失败
            req.setAttribute("msg", "用户注册失败,稍后再试!");
        }


        return  "jsp/info.jsp";



    }
    //验证用户名
    public String userExists(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //接收参数
        String username = req.getParameter("username");
        //调用业务层逻辑
        UserService userService = (UserService) BeanFactory.createObject("UserService");
        User userByUsername = userService.findUserByUsername(username);
        //响应

        if (null != userByUsername) {
            resp.getWriter().print("11");
        } else {
            resp.getWriter().print("00");
        }
        return null;

    }
    //激活
    public String active(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //获取激活码

        String code = req.getParameter("code");

        //调用业务层激活功能

        UserService user = (UserService) BeanFactory.createObject("UserService");
        boolean flag=user.userActive(code);

        //进行激活提示

            if (flag){
                //激活成功
                req.setAttribute("msg","用户激活成功请登入");
                return "jsp/login.jsp";

            }else{
                //激活失败
                req.setAttribute("msg","激活失败，请重新激活");
                return "jsp/info.jsp";
            }


    }
    //登录页
    public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "jsp/login.jsp";
    }
    //登录
    public String userLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        //获取用户数据（账号/密码）

        User user = Utils.populate(User.class, req.getParameterMap());
        //调用业务层登录功能


        UserService userService = (UserService) BeanFactory.createObject("UserService");

        User user02 =null;

        try {
            //查询
            user02 = userService.userLogin(user);
            //登录成功将用户信息放在session中
            req.getSession().setAttribute("loginUser",user02);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
            return null;

        } catch (SQLException e) {
            //登录失败
            e.printStackTrace();
            String message = e.getMessage();
            req.setAttribute("msg",message);
            return "/jsp/login.jsp";

        }


    }
    //退出
    public String logOut(HttpServletRequest req, HttpServletResponse resp) throws Exception{
       //清除session
        req.getSession().invalidate();
        //返回主页
        resp.sendRedirect(req.getContextPath()+"/jsp/index.jsp");
        return null;
    }



}
