package dao.Imp;

import dao.ProductDao;
import domain.PageModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import web.tools.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImp implements ProductDao {
    @Override
    public List findProducrs(int currentNum, int pageSize) throws SQLException {

        //查询产品信息

        String sql="select * from product limit ?,?";

        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());


        return queryRunner.query(sql,new BeanListHandler<PageModel>(PageModel.class),currentNum,pageSize);
    }

    @Override
    public int findToralRecords() throws SQLException {

        String sql="select  count(*) from product limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long query = (Long)queryRunner.query(sql, new ScalarHandler());
        return query.intValue();
    }
}
