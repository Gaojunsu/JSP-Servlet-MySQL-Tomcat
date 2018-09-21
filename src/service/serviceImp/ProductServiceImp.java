package service.serviceImp;

import dao.ProductDao;
import dao.daoImp.ProductDaoImp;
import domain.Category;
import domain.PageBean;
import domain.PageModel;
import domain.Product;
import service.ProductService;
import web.tools.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp  implements ProductService {
    @Override
    public List<Product> getHots() throws Exception {

        ProductDao productDa = (ProductDao) BeanFactory.createObject("ProductDao");
        return productDa.getHots();
    }
    @Override
    public List<Product> getNews() throws Exception {
        ProductDao productDa = (ProductDao) BeanFactory.createObject("ProductDao");
        return productDa.getNews();
    }
    @Override
    public Product findProductByPid(String pid) throws Exception {
        ProductDao productDa = (ProductDao) BeanFactory.createObject("ProductDao");
        return productDa.findProductByPid(pid);
    }
    @Override
    public PageBean<Product> findByCid(String cid, int pageNum, int pageSize) {
        return null;
    }
    @Override
    public PageModel findProductsByCidWithPage(String cid, int currentNum) throws Exception {
        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");
        //获取总条数
        int totalRecords=productDao.findToralRecordByCid(cid);
        //创建model对象，计算分页
        PageModel pm=new PageModel(currentNum,12,totalRecords);
        List producrs=productDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
        //关联集合
        pm.setList(producrs);
        //3_关联url
        pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
        return pm;
    }

    @Override
    public PageModel findAll(int currentPage) throws Exception {

        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");
        //获取总条数
        int totalRecords=productDao.findToralRecord();
        //创建model对象，计算分页
        PageModel pm=new PageModel(currentPage,12,totalRecords);
        List producrs=productDao.findAll(pm.getStartIndex(),pm.getPageSize());
        //关联集合
        pm.setList(producrs);
        //3_关联url
        pm.setUrl("AddProductServlet?method=findAllProduct");
        return pm;
    }

    @Override
    public List<Category>  findCategory() throws Exception {
        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");
        return  productDao.findCategory();
    }

    @Override
    public void saveProduct(Product product) throws Exception {
        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");
        productDao.saveProduct(product);
    }

    @Override
    public void deleteProduct(String pid) throws Exception {
        ProductDao productDao=(ProductDao) BeanFactory.createObject("ProductDao");
        productDao.deleteProduct(pid);
    }

    @Override
    public PageModel findProducts(int currentNum, int pageSize) throws SQLException {

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
