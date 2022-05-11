package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.util.Properties;

public class Util implements AutoCloseable {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "short";
    private static final String URL = "jdbc:mysql://localhost:3306/db_hibernate";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, DIALECT);
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    //    public static Connection activeConnection() {
//        try {
//            Class.forName(DRIVER);
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Connection is OK");
//            //connection.close();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    public static Connection activeConnection() { // method getConnect
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
