package domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cart {


    //总计
    private  double total=0;
    //个数不确定的购物项商品pid   CartItem
    Map<String, CartItem> map=new HashMap<String, CartItem>();
    //添加购物项到购物车
    //当用户点击加入购物车按钮，可以将当前要购买商品的id，商品数量发到服务端，服务端根据商品ID查询商品信息
    //商品信息Product ，购买数量，购物项
    public  void addCartItemToCar(CartItem careItem){
        //正在想购物车中添加的商品ID   PK
        String pid = careItem.getProduct().getPid();
        //购物车是否已经存在相同的商品
        if (map.containsKey(pid)){
            //存在
            CartItem oldCart = map.get(pid);
            oldCart.setCount(oldCart.getCount()+careItem.getCount());
        }else{
            //不存在
            map.put(pid,careItem);
        }
    }

     //获取所以的CartItem
    public Collection <CartItem>getCartItems(){
        return map.values();
    }

    //移除购物项
    public void removeCartItem(String pid){
        map.remove(pid);
    }
    //清空购物项
    public void clearCart(){
        map.clear();
    }
    public double getTotal() {
        //初始化
        total=0;
        for (CartItem c : map.values()) {
            //加总
            total+=c.getSubTatol();
        }
        return total;
    }


    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }
}
