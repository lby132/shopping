package com.example.repository;

import com.example.entity.CusPro;
import com.example.entity.CusProProduct;
import com.example.entity.Customer;
import com.example.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ShopMyBatisDAO { // MyBatis API
      // DB연결 -> config.xml(환경설정파일) -> API read 연결작업을 대신 해주면 된다.
     private static SqlSessionFactory sqlSessionFactory; // Connection(SqlSession) Pool
      static{  // 초기화 블럭
           try{
               String resource = "mybatis-config/config.xml";
               InputStream inputStream = Resources.getResourceAsStream(resource);
               sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
           }catch(Exception e){
               e.printStackTrace();
           }
      }

    public List<Product> productList() {
        final SqlSession session = sqlSessionFactory.openSession();

        final List<Product> list = session.selectList("productList");
        session.close();
        return list;
    }

    public Customer customer_login(Customer cus) {
        final SqlSession session = sqlSessionFactory.openSession();
        Customer cusDto = session.selectOne("customer_login", cus);
        session.close();
        return cusDto;
    }

    public int cartAdd(CusPro dto) {
        SqlSession session = sqlSessionFactory.openSession();

        CusPro checkDto = session.selectOne("checkAdd", dto);

        int cnt = -1;

        if (checkDto != null) {
            cnt = session.update("cartUpdate", dto);
        } else {
            cnt = session.insert("cartAdd", dto);
        }
        session.commit();
        session.close();
        return cnt;
    }

    public List<CusProProduct> cartList(String customer_id) {
        SqlSession session = sqlSessionFactory.openSession();

        List<CusProProduct> list = session.selectList("cartList", customer_id);

        session.close();

        return list;
    }

    public int totalAmount(String customer_id) {
        SqlSession session=sqlSessionFactory.openSession();
        int totalAmount=session.selectOne("totalAmount", customer_id);
        session.close();
        return totalAmount;
    }

    public int cartCancel(int order_number) {
        final SqlSession session = sqlSessionFactory.openSession();
        final int cnt = session.delete("cartCancel", order_number);
        session.commit();
        session.close();
        return cnt;
    }

    public int cartEmpty(String customer_id) {
        final SqlSession session = sqlSessionFactory.openSession();
        final int cnt = session.delete("cartEmpty", customer_id);
        session.commit();
        session.close();
        return cnt;
    }

    public int pointSave(Customer cus) {
        final SqlSession session = sqlSessionFactory.openSession();
        final int cnt = session.update("pointSave", cus);
        session.commit();
        session.close();
        return cnt;
    }

    public int updateQuantity(CusPro dto) {
        final SqlSession session = sqlSessionFactory.openSession();
        final int cnt = session.update("updateQuantity", dto);
        session.commit();
        session.close();
        return cnt;
    }
}
