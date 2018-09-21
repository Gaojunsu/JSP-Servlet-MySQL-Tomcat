package web.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;





public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取客户端提交到服务端的method值
        String md = req.getParameter("method");
       if (null==md||"".equals(md)||md.trim().equals("")){
           md="execute";
       }


        //获取当前字节码对象（内存中的对象）
        Class  aClass = this.getClass();

        try {
            //获取md对应方法
            Method method = aClass.getMethod(md, HttpServletRequest.class, HttpServletResponse.class);

            if (null!=method){
                //调用方法
               String path = (String) method.invoke(this, req, resp);
                if (null!=path){
                    req.getRequestDispatcher(path).forward(req,resp);
                }

            }



        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    // 默认方法
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        return null;
    }
}
