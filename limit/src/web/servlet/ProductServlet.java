package web.servlet;

import domain.PageModel;
import service.ProductService;
import service.serviceImp.ProductServiceImp;
import web.tools.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductServlet  extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at:").append(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //接收当前页
        String num = req.getParameter("num");
        int currentNum = Integer.parseInt(num);
             //调用业务层逻辑查询当前页功能，返回pageModel对象

        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");;
        PageModel pm=null;

        try {
            pm=productService.findProducts(currentNum,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //放入requ，传入view
        req.setAttribute("page",pm);
        //重定向
        req.getRequestDispatcher("/product_list2.jsp").forward(req,resp);

    }
}
