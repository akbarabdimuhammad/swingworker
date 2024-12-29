package model;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static SqlSessionFactory sessionFactory;

    static {
        try {
  
            String resource = "mybatis-config.xml";
            InputStream inputStream = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
        }
    }

    public static SqlSession getSqlSession() {
        return sessionFactory.openSession();
    }
}
