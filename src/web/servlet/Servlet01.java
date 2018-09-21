package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/Servlet01")
public class Servlet01 extends HttpServlet {




    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path=null;

        //获取提交数据
        String method = req.getParameter("method");
        if("add".equals(method)){
            path=add(req,resp);
        }else if("del".equals(method)){
            path=del(req,resp);
        }else if("check".equals(method)){
            path=cehck(req,resp);
        }else if("updateStu".equals(method)){

        }

        if (path!=null){
            //服务端转发
            req.getRequestDispatcher(path).forward(req,resp);
        }

    }

    //增加
    protected String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("增加");
    return "";

    }
    //删除
    protected String del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("删除");
    return "";

    }
    //检查
    protected String cehck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("检查");
        return "";

    }

}
