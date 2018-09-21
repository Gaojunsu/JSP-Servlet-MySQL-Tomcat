package web.servlet;

import domain.Category;
import domain.PageModel;
import domain.Product;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import service.CategoryService;
import service.ProductService;
import service.serviceImp.CategoryServiceImp;
import service.serviceImp.ProductServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;
import web.tools.UUIDUtils;
import web.tools.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductServlet extends BaseServlet{



    public String findAllProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        int currentPage = Integer.parseInt(req.getParameter("num"));
        //調用業務層查詢所有商品信息
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        PageModel pm = productService.findAll(currentPage);
        //放入requ，传入view
        req.setAttribute("page",pm);
        return "/admin/product/list.jsp";
    }
    public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //携带表单中的数据向servcie,dao
        Product product=new Product();
        //獲取每個部分 講遍歷的值存入map
        Map<String,String>map=new HashMap<>();
        try {
            //創建磁盤文件工廠
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //設置緩存區大小:如果文件超過緩衝區大小就會產生臨時文件
             diskFileItemFactory.setSizeThreshold(3*1024*1024);
            //獲取核心解析類
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //解決中文上傳亂碼問題
            servletFileUpload.setHeaderEncoding("utf-8");
            //設置單個文件大小   servletFileUpload.setFileSizeMax();
            //設置表單中所以文件項的文件總大小  servletFileUpload.setSizeMax();
            //解析返回的request集合
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem:list) {
                if (fileItem.isFormField()){
                    //如果fileItem是普通項
                    //将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
                    map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
                }else{
                    //如果fileItem是上傳項

                    //通過原有的文件名稱生成新文件名稱
                    String oldFileName = fileItem.getName();
                    String newFileName = UploadUtils.getUUIDName(oldFileName);
                    //獲取輸入流,二進制數據
                    InputStream inputStream = fileItem.getInputStream();
                    //當前項目下的路徑,存放上傳的圖片數據   C:\Users\Jean_Su\Desktop\webFeature\web\products\1
                    String realPath = getServletContext().getRealPath("/products/1/");
                    //内存中声明一个目录
                    File newDir=new File(realPath);
                    if(!newDir.exists()){
                        newDir.mkdirs();
                    }
                    //在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
                    File finalFile=new File(newDir,newFileName);
                    if(!finalFile.exists()){
                        finalFile.createNewFile();
                    }
                    //建立和空文件对应的输出流
                    OutputStream os=new FileOutputStream(finalFile);
                    //将输入流中的数据刷到输出流中
                    IOUtils.copy(inputStream, os);
                    //释放资源
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly(os);
                    map.put("pimage", "products/1/"+newFileName);
                }
            }

            //7_利用BeanUtils将MAP中的数据填充到Product对象上
            BeanUtils.populate(product, map);
            product.setPid(UUIDUtils.getId());
            product.setPdate(new Date());
            product.setPflag(0);

            //8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
            ProductService ProductService=(ProductService) BeanFactory.createObject("ProductService");
            ProductService.saveProduct(product);

            resp.sendRedirect("/AddProductServlet?method=findAllProduct&num=1");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
    public String editProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //調用業務層查詢所有商品分類信息
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        List<Category>  categorys=productService.findCategory();
        //放入requ，传入view
        req.setAttribute("categorys",categorys);
        return "/admin/product/add.jsp";
    }

    public String deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String num = req.getParameter("num");
        String pid = req.getParameter("pid");

        //調用業務層查詢所有商品分類信息
        ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
        productService.deleteProduct(pid);

        resp.sendRedirect("/AddProductServlet?method=findAllProduct&num="+num);
        return null;
    }


}
