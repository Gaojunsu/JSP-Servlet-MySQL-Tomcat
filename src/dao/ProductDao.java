package dao;

import domain.Category;
import domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> getHots() throws Exception;
    List<Product> getNews() throws Exception;
    Product findProductByPid(String pid) throws Exception;
    List findProducrs(int currentNum, int pageSize) throws SQLException;
    int findToralRecords()  throws SQLException;
    int findToralRecordByCid(String cid) throws Exception;
    List findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception;
    List<Product> findAll(int currentPage, int pageSize)throws Exception;
    int findToralRecord()throws Exception;
    List<Category> findCategory() throws Exception;
    void saveProduct(Product product)throws Exception;

    void deleteProduct(String pid)throws Exception;
}
