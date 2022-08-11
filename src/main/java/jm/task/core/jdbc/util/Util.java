package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/myusers", "root", "MySqlElinel1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Session getSession() {
        return new Configuration().addAnnotatedClass(User.class)
                .setProperty("hibernate.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/myusers")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "MySqlElinel1")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                .setProperty("hibernate.current_session_context_class", "thread")
                .buildSessionFactory().getCurrentSession();
    }

}
