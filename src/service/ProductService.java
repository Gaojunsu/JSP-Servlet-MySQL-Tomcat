package service;

import domain.Category;
import domain.PageBean;
import domain.PageModel;
import domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> getHots() throws Exception;
    List<Product> getNews() throws Exception;
    Product findProductByPid(String pid) throws Exception;
    PageModel findProducts(int currentNum, int pageSize) throws SQLException;
    PageBean<Product> findByCid(String cid, int pageNum, int pageSize);
    PageModel findProductsByCidWithPage(String cid,int currentNum) throws Exception;
    PageModel findAll(int currentPage) throws Exception;
    List<Category>  findCategory() throws Exception;
    void saveProduct(Product product)throws Exception;
    void deleteProduct(String pid)throws Exception;

}
