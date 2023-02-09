package com.aina.headlines;

import com.aina.headlines.model.Headline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        HibernateDao dao = context.getBean(HibernateDao.class);
        System.out.println(dao);


        List<Headline> headlines = dao.personalFind(new Headline(), 6, 6, "ma");
        System.out.println(headlines.size());
    }
}
