package service;

import domain.Order;
import domain.OrderItem;
import domain.PageModel;
import domain.User;

public interface OrderService {
    void savaOrder(Order order)throws Exception;

    PageModel findOrdersByUidWithPage(User user, int num)throws Exception;

    Order findOrderByOid(String oid) throws Exception;

    void updateOrder(Order order) throws Exception;
}
