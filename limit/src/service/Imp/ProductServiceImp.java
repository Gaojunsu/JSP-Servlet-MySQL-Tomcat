package service.Imp;

import dao.Imp.ProductDaoImp;
import dao.ProductDao;
import domain.PageModel;
import service.ProductService;
import web.tools.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp implements ProductService{
    @Override
    public PageModel findProducts(int currentNum,int pageSize) throws SQLException {

        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");

        //获取总条数
        int totalRecords=productDao.findToralRecords();
        //创建model对象，计算分页
        PageModel pm=new PageModel(currentNum,5,totalRecords);
        List producrs = productDao.findProducrs(currentNum, pageSize);
        pm.setList(producrs);
        //3_关联url
        pm.setUrl("ProductServlet");
        return pm;
    }
}
