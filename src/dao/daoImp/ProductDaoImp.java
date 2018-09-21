package dao.daoImp;

import dao.ProductDao;
import domain.Category;
import domain.PageModel;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import web.tools.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImp implements ProductDao{
    @Override
    public List<Product> getHots() throws Exception {

        String sql="SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class));


    }

    @Override
    public List<Product> getNews() throws Exception {

        String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class));


    }

    @Override
    public Product findProductByPid(String pid) throws Exception {


                     String sql="Select * from product where pid=?";
                     QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
                     return queryRunner.query(sql,new BeanHandler<Product>(Product.class),pid);

    }
    @Override
    public List findProducrs(int currentNum, int pageSize) throws SQLException {

        //查询产品信息

        String sql="select * from product limit ?,?";

        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());


        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),currentNum,pageSize);
    }

    @Override
    public int findToralRecords() throws SQLException {

        String sql="select  count(*) from product";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long query = (Long)queryRunner.query(sql, new ScalarHandler());
        return query.intValue();
    }

    @Override
    public int findToralRecordByCid(String cid) throws Exception {

        String sql="select  count(*) from product where cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long query = (Long)queryRunner.query(sql, new ScalarHandler(),cid);
        return query.intValue();


    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {

        String sql="select *  from product where cid=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
    }

    @Override
    public List<Product> findAll(int currentPage, int pageSize) throws Exception {

        String sql="select *  from product   limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),currentPage,pageSize);

    }

    @Override
    public int findToralRecord() throws Exception {
        String sql="select  count(*) from product ";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long query = (Long)queryRunner.query(sql, new ScalarHandler());
        return query.intValue();
    }

    @Override
    public List<Category> findCategory() throws Exception {
        String sql="select * from category";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<>(Category.class));
    }

    @Override
    public void saveProduct(Product product) throws Exception {


        String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
        qr.update(sql,params);

    }

    @Override
    public void deleteProduct(String pid) throws Exception {
        String sql="delete from product where pid=?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,pid);

    }
}
