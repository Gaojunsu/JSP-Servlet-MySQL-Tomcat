package web.servlet;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import service.ProductService;
import service.serviceImp.ProductServiceImp;
import web.base.BaseServlet;
import web.tools.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet {


    public String addToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //session中获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null==cart){
            //没有，创建购物车
            cart=new Cart();
            //存在session里面
            req.getSession().setAttribute("cart",cart);
        }


            //有直接获取使用
            //获取商品ID,数量
            String pid=req.getParameter("pid");
            int qty= Integer.parseInt(req.getParameter("num"));
            //通过ID查询出商品对象
            ProductService productService=(ProductService) BeanFactory.createObject("ProductService");
            Product product = productService.findProductByPid(pid);
            //获取待购买的购物项
            CartItem cartItem = new CartItem();
            cartItem.setCount(qty);
            cartItem.setProduct(product);
            //加入购物车
             cart.addCartItemToCar(cartItem);

             resp.sendRedirect("/jsp/cart.jsp");

        return null;

    }
    public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //session中获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取商品ID
        String pid=req.getParameter("pid");
        //删除商品
        cart.removeCartItem(pid);
        resp.sendRedirect("/jsp/cart.jsp");
        return null;

    }
    public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //session中获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //删除商品
        cart.clearCart();
        resp.sendRedirect("/jsp/cart.jsp");
        return null;

    }

}
