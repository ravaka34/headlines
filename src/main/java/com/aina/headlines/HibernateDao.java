package com.aina.headlines;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.*;

import java.io.Serializable;
import java.util.List;

//Hibernate 3.0
public class HibernateDao {

    private SessionFactory sessionFactory;

    public <T> T create(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public <T> T findById(Class<T> clazz,Serializable id){
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass){
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity);
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    public <T> List<T> paginateWhere (T entity, int offset, int size){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
                .add(example)
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size){
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc){
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
                .addOrder(order)
                .setFirstResult(offset)
                .setMaxResults(offset+size) .list();
        session.close();
        return results;
    }

    public <T> List<T> personalFind (Class<T> clazz, int offset, int size, String word){
        Session session = sessionFactory.openSession();
        word = "%"+word+"%";
        List<T> results = session.createCriteria(clazz)
                .add(
                        Restrictions.or(
                                Restrictions.ilike("title", word, MatchMode.ANYWHERE),
                                Restrictions.ilike("body", word, MatchMode.ANYWHERE)
                        )
                )
                .addOrder(Order.asc("id"))
                .setFirstResult(offset)
                .setMaxResults(offset + size)
                .list();
        session.close();
        return results;
    }

    public <T> List<T> personalFind (T entity, int offset, int size, String word){
        Session session = sessionFactory.openSession();
        word = "%"+word+"%";
        Example example = Example.create(entity);
        List<T> results = session.createCriteria(entity.getClass())
                .add(example)
                .add(
                        Restrictions.or(
                                Restrictions.ilike("title", word, MatchMode.ANYWHERE),
                                Restrictions.ilike("body", word, MatchMode.ANYWHERE)
                        )
                )
                .addOrder(Order.desc("id"))
                .setFirstResult(offset)
                .setMaxResults(offset + size)
                .list();
        session.close();
        return results;
    }

    public <T> Long personalCount (T entity, String word){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity);
        long count = (Long) session.createCriteria(entity.getClass())
                .setProjection(Projections.rowCount())
                .add(example)
                .add(
                        Restrictions.or(
                                Restrictions.ilike("title", word, MatchMode.ANYWHERE),
                                Restrictions.ilike("body", word, MatchMode.ANYWHERE)
                        )
                )
                .uniqueResult();
        session.close();
        return count;
    }


    public Long personalCount (Class clazz, String word){
        Session session = sessionFactory.openSession();
        long count = (Long) session.createCriteria(clazz)
                .setProjection(Projections.rowCount())
                .add(
                    Restrictions.or(
                            Restrictions.ilike("title", word, MatchMode.ANYWHERE),
                            Restrictions.ilike("body", word, MatchMode.ANYWHERE)
                    )
                )
                .uniqueResult();
        session.close();
        return count;
    }

    public void deleteById(Class tClass, Serializable id){
        delete(findById(tClass, id));
    }

    public void delete(Object entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
