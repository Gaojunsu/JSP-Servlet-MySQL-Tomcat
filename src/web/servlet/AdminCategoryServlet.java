package web.servlet;

import domain.Category;
import service.CategoryService;
import service.serviceImp.CategoryServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

public class AdminCategoryServlet extends BaseServlet{


    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取全部分类信息
        CategoryService CategoryService=(CategoryService) BeanFactory.createObject("CategoryService");
        List<Category> list=CategoryService.getAllCats();
        //全部分类信息放入request
        req.setAttribute("allCats", list);
        //转发到/admin/category/list.jsp
        return "/admin/category/list.jsp";
    }
    public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/admin/category/add.jsp";
    }
    public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {

            //獲取提交過來的分類信息
        String cname = req.getParameter("cname");
        //創建分類
        Category category = new Category();
        category.setCname(cname);
        category.setCid(UUID.randomUUID().toString());
        //調用業務層保存新增分類信息
        CategoryService categoryService=(CategoryService) BeanFactory.createObject("CategoryService");
        categoryService.addCategory(category);

        return "/admin/category/add.jsp";
    }
    public String editCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //獲取提交過來的分類信息
        String cid = req.getParameter("cid");

        //調用業務層查詢分類信息
        CategoryService categoryService=(CategoryService) BeanFactory.createObject("CategoryService");
        Category category=categoryService.findByCid(cid);
        req.setAttribute("category",category);
        return "/admin/category/edit.jsp";
    }
    public void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //獲取提交過來的分類信息
        String cid = req.getParameter("cid");
        //調用業務層查詢分類信息
        CategoryService categoryService=(CategoryService) BeanFactory.createObject("CategoryService");
        categoryService.deleteByCid(cid);
        resp.sendRedirect(req.getContextPath()+"/AdminCategoryServlet?method=findAllCats");
    }
}
