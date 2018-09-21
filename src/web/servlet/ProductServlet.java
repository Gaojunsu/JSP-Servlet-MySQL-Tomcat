package web.servlet;

import domain.PageBean;
import domain.PageModel;
import domain.Product;
import service.ProductService;
import service.serviceImp.ProductServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductServlet extends BaseServlet{


    public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //根据pid查询对应商品详情
        String pid = req.getParameter("pid");
        //调用业务层查询
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        Product product=productService.findProductByPid(pid);
        //转发至product_info页面
        req.setAttribute("pro",product);

        return "/jsp/product_info.jsp";

    }
    public String findBy(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //根据pid查询对应商品详情
        String cid = req.getParameter("cid");
       //当前页
        int pageNum=1;
        try {
            pageNum= Integer.parseInt(req.getParameter("pageNum"));
        } catch (NumberFormatException e) { }
        //每页显示
        int pageSize=12;

        //调用业务层逻辑
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        PageBean<Product> pageBean=productService.findByCid(cid,pageNum,pageSize);

        return "/jsp/product_info.jsp";

    }
    public void findByCid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收当前页
        String num = req.getParameter("num");
        int currentNum = Integer.parseInt(num);
        //调用业务层逻辑查询当前页功能，返回pageModel对象

        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        PageModel pm=null;

        try {
            pm=productService.findProducts(currentNum,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //放入requ，传入view
        req.setAttribute("page",pm);
        //重定向
        req.getRequestDispatcher("/jsp/product_list2.jsp").forward(req,resp);

    }
    public String findProductsByCidWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //接收当前页参数 num页数 cid 分类ID
        String num = req.getParameter("num");
        int currentNum = Integer.parseInt(num);
        String cid = req.getParameter("cid");
        //调用业务层逻辑查询当前页功能，返回pageModel对象
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        PageModel  pm=productService.findProductsByCidWithPage(cid,currentNum);
        //放入requ，传入view
        req.setAttribute("page",pm);
        //重定向
        return  "/jsp/product_list.jsp";

    }
}
