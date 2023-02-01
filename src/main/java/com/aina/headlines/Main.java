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

        List<Headline> headlines = dao.personalFind(Headline.class, 6, 6, "");
        System.out.println(headlines.get(1).getId());
    }
}
