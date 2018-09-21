package domain;

public class CartItem {

    //购买商品的信息
    private Product product;
    //购买数量
    private int count;
    //总计 商品价格*数量
    private  double subTatol;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubTatol() {

        this.subTatol=product.getShop_price()*count;
        return this.subTatol;
    }


}
