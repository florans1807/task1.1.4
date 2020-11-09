package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (Id MEDIUMINT NOT NULL AUTO_INCREMENT" +
                    ", firstname VARCHAR(30) NOT NULL, " +
                    "lastname VARCHAR(30) NOT NULL, Age MEDIUMINT NOT NULL, PRIMARY KEY (id));").executeUpdate();
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            session.save(user);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            Query query = session.createQuery("delete from User where id = :paramName");
            query.setParameter("paramName", id);
            query.executeUpdate();
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction ts = null;
        try (Session session = sessionFactory.openSession()) {
            ts = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }
}
