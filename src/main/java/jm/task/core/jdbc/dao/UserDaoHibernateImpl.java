package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
       /* Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "CREATE TABLE IF NOT EXISTS users (id MEDIUMINT NOT NULL AUTO_INCREMENT" +
                ", firstname VARCHAR(30) NOT NULL, " +
                "lastname VARCHAR(30) NOT NULL, Age TINYINT NOT NULL, PRIMARY KEY (id));";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        //session.flush();
        session.close();*/

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id MEDIUMINT NOT NULL AUTO_INCREMENT" +
                    ", firstname VARCHAR(30) NOT NULL, " +
                    "lastname VARCHAR(30) NOT NULL, Age TINYINT NOT NULL, PRIMARY KEY (id));").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DROP TABLE IF EXISTS users";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.flush();
        session.close();*/

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        //session.flush();
        session.close();*/
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from User where id = :paramName");
        query.setParameter("paramName", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();*/

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("delete from User where id = :paramName");
            query.setParameter("paramName", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT * FROM users";
        //String hql = "FROM User";
        Query query = session.createSQLQuery(hql);
        List<User> users = query.list();
        session.close();
        return users;*/

        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createSQLQuery("SELECT * FROM users").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        //session.flush();
        session.close();*/

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
