package dao.daoImp;

import dao.CategoryDao;
import domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import web.tools.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImp implements CategoryDao{
    @Override
    public List<Category> getAllCategorys() throws SQLException {
        String sql="select * from category";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        String sql="insert into category values(?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql,category.getCid(),category.getCname());
    }

    @Override
    public Category findByCid(String cid) throws SQLException {
        String sql="select * from category where cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanHandler<>(Category.class),cid);
    }

    @Override
    public void deleteByCid(String cid) throws SQLException {
        String sql="Delete  from category where cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql,cid);
    }
}
