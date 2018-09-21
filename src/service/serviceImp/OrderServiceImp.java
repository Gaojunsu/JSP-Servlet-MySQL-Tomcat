package service.serviceImp;

import dao.OrderDao;
import dao.daoImp.OrderDaoImp;
import domain.Order;
import domain.OrderItem;
import domain.PageModel;
import domain.User;
import service.OrderService;
import web.tools.BeanFactory;
import web.tools.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrderService{


    OrderDao orderDao=(OrderDao) BeanFactory.createObject("OrderDao");

    @Override
    public void savaOrder(Order order) throws Exception {
     /*   //保存訂單和訂單下所有的訂單項(同時成功或同時失敗)
        //開啟事務
        try {
            JDBCUtils.startTransaction();
            OrderDao orderDao=new OrderDaoImp();
            orderDao.savaOrder(order);
            for (OrderItem item :order.getList()) {
                        orderDao.savaOrderItem(item);
            }
            //提交
            JDBCUtils.commitAndClose();
        } catch (SQLException e) {
            //回滾
          JDBCUtils.rollbackAndClose();
        }*/
        Connection connection =null;
        try {
            //獲取連接
            connection = JDBCUtils.getConnection();
            //開啟事務
            connection.setAutoCommit(false);
            //保存訂單項


            orderDao.saveOrder(connection,order);
            for (OrderItem item : order.getList()) {
                orderDao.saveOrderItem(connection,item);
            }
            //提交
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
    }

    @Override
    public PageModel findOrdersByUidWithPage(User user, int num) throws Exception{

            int count=orderDao.getTatolRecord(user);
         PageModel pm=new PageModel(num, count, 3);
        //2_关联集合  select * from orders where uid=? limit ? ,?
        List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //3_关联url
        pm.setUrl("OrderServlet?method=findOrdersByUidWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
       Order order= orderDao.findOrderByOid(oid);


        return order;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        orderDao.updateOrder(order);
    }


}
