package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }



    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "CREATE TABLE IF NOT EXISTS users (id MEDIUMINT NOT NULL AUTO_INCREMENT" +
                ", name VARCHAR(30) NOT NULL, " +
                "lastName VARCHAR(30) NOT NULL, age INT NOT NULL, PRIMARY KEY (id));";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        //session.flush();
        //session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "DROP TABLE IF EXISTS users";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM users where id = :paramName";
        Query query = session.createQuery(hql);
        query.setParameter("paramName", id);
        List<User> users = query.list();
        query.executeUpdate();
        session.delete(users.get(0));
        session.getTransaction().commit();
        /*session.flush();
        session.close();*/
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "SELECT * FROM users";
        //String hql = "FROM User";
        Query query = session.createSQLQuery(hql);
        List<User> users = query.list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        //session.flush();
        //session.close();
    }
}
