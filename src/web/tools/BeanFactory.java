package web.tools;

import dao.UserDao;
import domain.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BeanFactory {


    public static Object createObject(String className) {


        try {

            //獲取document對象
            SAXReader saxReader = new SAXReader();

            //獲取輸入流
            InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
            Document document = saxReader.read(resourceAsStream);
            //通過document對象獲取節點信息

            //根節點
            Element rootElement = document.getRootElement();
            //獲取根節點下所有子節點
            List<Element> elements = rootElement.elements();
            //遍歷所有子節點,獲取信息
            for (Element element:elements) {
                String id = element.attributeValue("id");
                //判斷是否我需想要創建的對象名
                if (id.equals(className)){
                    String aClass = element.attributeValue("class");
                    //通過反射創建對象 并返回
                      Class c=  Class.forName(aClass);
                      return c.newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {


        //測試  通過bean工廠  創建對象 註冊用戶
        UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");

        User user = new User();
        user.setName("QQP");
        user.setUid(UUID.randomUUID().toString());
        user.setEmail("QQP@qq.com");
        user.setUsername("qqp");
        user.setPassword("qqppqqppq");
        userDao.regist(user);

    }

}
