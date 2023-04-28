// equivalent to hibernate.cfg.xml

package com.project.alumninetwork.util;


import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;


import com.project.alumninetwork.pojo.*;
@Component
public class HibernateUtil {
	
	  private static SessionFactory sessionFactory;
	  private static BootstrapServiceRegistry bootstrapServiceRegistry;
	  
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Project_DB?createDatabaseIfNotExist=true");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "root@1234");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	                settings.put(Environment.HBM2DDL_AUTO, "update");

	                configuration.setProperties(settings);

	                configuration.addAnnotatedClass(User.class);
	                configuration.addAnnotatedClass(Student.class);
	                configuration.addAnnotatedClass(StudentUser.class);
	                configuration.addAnnotatedClass(Alumni.class);
	                configuration.addAnnotatedClass(AlumniUser.class);
	                configuration.addAnnotatedClass(Job.class);
	     
	                
	                
	                bootstrapServiceRegistry = new BootstrapServiceRegistryBuilder()
							.applyClassLoader(Thread.currentThread().getContextClassLoader())
							.build();


	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder(bootstrapServiceRegistry)
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}
