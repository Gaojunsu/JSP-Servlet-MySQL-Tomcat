package web.servlet;

import domain.Category;
import net.sf.json.JSONArray;
import service.CategoryService;
import service.serviceImp.CategoryServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategroyServlet  extends BaseServlet {


    //findAllCats  redis
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {



        //调用业务层获取全部分类
        CategoryService categoryService =(CategoryService) BeanFactory.createObject("CategoryService");
        List<Category> allCats = categoryService.getAllCats();
        //转换为JSON格式数据
        //将信息返回到客户端
        String s = JSONArray.fromObject(allCats).toString();
        //设置响应数据类型JSON格式
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(s);


        return null;
    }
}
