
package spittr.db.hibernate4;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spittr.domain.Spitter;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RepositoryTestConfig.class)
@ContextConfiguration("classpath:spittr/db/hibernate4/RepositoryTest-context.xml")
public class OtherTest {

    @Autowired
    SessionFactory sessionFactory;

    //HQL:hibernate query language，即hibernate提供的面向对象的查询语言
    @Test
    @Transactional
    public void hqlTest1() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Spitter");
        List<Spitter> list = query.list();
        System.out.println(list);
    }

    @Test
    @Transactional
    public void hqlTest2() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Spitter where id = ?");
        query.setParameter(0, 2L);
        List<Spitter> list = query.list();
        System.out.println(list);
    }


    //QBC查询: query by criteria 完全面向对象的查询
    @Test
    @Transactional
    public void qbcTest() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Spitter.class);
        criteria.add(Restrictions.eq("id", 2L));
        List<Spitter> list = criteria.list();
        System.out.println(list);
    }

    //本地SQL查询
    @Test
    @Transactional
    public void sqlTest() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM spitter").addEntity(Spitter.class);
        List<Spitter> list = sqlQuery.list();
        System.out.println(list);
    }
}