# JSP-Servlet-MySQL-Tomcat

一個入門級JAVA新手學習項目實例   

   #### 註冊-登錄-訂單-支付   商品分類增刪

### sql語句 自備基礎數據:
  -- INSERT INTO `product`(`pid`, `pname`, `market_price`, `shop_price`, `pimage`, `pdate`, `is_hot`, `pdesc`, `pflag`, `cid`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10])

-- INSERT INTO `category`(`cid`, `cname`) VALUES ([value-1],[value-2])

-- INSERT INTO `orderitem`(`itemid`, `quantity`, `total`, `pid`, `oid`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])  

-- INSERT INTO `orders`(`oid`, `ordertime`, `total`, `state`, `address`, `name`, `telephone`, `uid`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8])

-- INSERT INTO `user`(`uid`, `username`, `password`, `name`, `email`, `telephone`, `birthday`, `sex`, `state`, `code`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10])

### 表結構:  

-- product pid PK  cid FK(category)   產品-分類
-- category cid PK   分類
-- orderitem  oid PK  pid FK(product )   訂單-產品
-- orders   uid FK(user)  oid FK(orderitem)    訂單總匯-用戶
-- user uid  用戶

### 開始Start: 
-- web下   index.jsp   localhost:8081/index.jsp
