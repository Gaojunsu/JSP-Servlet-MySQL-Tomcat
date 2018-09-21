package web.servlet;

import domain.Category;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import web.base.BaseServlet;
import web.tools.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;




public class IndexServlet extends BaseServlet {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {




        //获取全部分类信息，集合
        CategoryService categoryService = (CategoryService) BeanFactory.createObject("CategoryService");
        List<Category> list =categoryService.getAllCats();
        //将集合返回request
        //转发到真实的主页
        req.setAttribute("allCats",list);

        ProductService productService = (ProductService) BeanFactory.createObject("ProductService");
        List<Product> products=productService.getHots();
        List<Product> productList=productService.getNews();

        //转发到真实的主页
        req.setAttribute("news",products);
        req.setAttribute("hots",productList);


        return "/jsp/index.jsp";



    }
}
