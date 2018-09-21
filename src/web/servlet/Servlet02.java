package web.servlet;

import web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/Servlet02")
public class Servlet02 extends BaseServlet {




    //增加
    public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("增加");
        return "";

    }
    //删除
    public String del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("删除");
        return "";

    }
    //检查
    public String check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("检查");
        return "";

    }


}
