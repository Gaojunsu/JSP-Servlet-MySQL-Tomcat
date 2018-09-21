package dao.daoImp;

import dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import web.tools.JDBCUtils;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderDaoImp implements OrderDao {

    @Override
    public void saveOrder(Connection conn, Order order) throws Exception {
        String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        QueryRunner qr=new QueryRunner();
        Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
        qr.update(conn,sql,params);
    }
    @Override
    public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
        String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
        QueryRunner qr=new QueryRunner();
        Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
        qr.update(conn,sql,params);
    }
    @Override
    public int getTatolRecord(User user) throws Exception {
        String sql=" select count(*) from orders where uid=?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
        return num.intValue();
    }

    @Override
    public List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
        String sql="select * from orders where uid=? limit ? , ?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);

        //遍历所有订单
        for (Order order : list) {
            //获取到每笔订单oid   查询每笔订单下的订单项以及订单项对应的商品信息
            String oid=order.getOid();
            sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
            List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
            //遍历list
            for (Map<String, Object> map : list02) {
                OrderItem orderItem=new OrderItem();
                Product product=new Product();
                // 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
                // 1_创建时间类型的转换器
                DateConverter dt = new DateConverter();
                // 2_设置转换的格式
                dt.setPattern("yyyy-MM-dd");
                // 3_注册转换器
                ConvertUtils.register(dt, java.util.Date.class);

                //将map中属于orderItem的数据自动填充到orderItem对象上
                BeanUtils.populate(orderItem, map);
                //将map中属于product的数据自动填充到product对象上
                BeanUtils.populate(product, map);

                //让每个订单项和商品发生关联关系
                orderItem.setProduct(product);
                //将每个订单项存入订单下的集合中
                order.getList().add(orderItem);

            }
        }
        return list;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        //根據oid查詢訂單信息
        String sql="select * from orders where oid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Order order = queryRunner.query(sql, new BeanHandler<Order>(Order.class), oid);
        //查詢出關聯的商品信息
        sql="select * from orderitem o,product p where o.pid=p.pid and oid=?";
        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), oid);
        for (Map<String, Object> map:query) {
            OrderItem orderItem = new OrderItem();
            Product product = new Product();
            //創建一個時間類轉換器
            DateConverter dateConverter = new DateConverter();
            //設置時間格式
            dateConverter.setPattern("yyyy-MM-hh");
            //註冊
            ConvertUtils.register(dateConverter, Date.class);
            //Bean轉換
            BeanUtils.populate(orderItem,map);
            BeanUtils.populate(product,map);
            //關聯信息
            orderItem.setProduct(product);
            order.getList().add(orderItem);
        }

        return order;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        String sql="UPDATE orders SET ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
        qr.update(sql,params);
    }
}
