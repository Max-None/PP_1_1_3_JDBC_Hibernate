package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        new UserDaoJDBCImpl().createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        new UserDaoJDBCImpl().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            userList = session.createQuery("SELECT u FROM User u", User.class).getResultList();
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
