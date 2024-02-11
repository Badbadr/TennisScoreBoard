package org.example.edu;

import org.example.model.Player;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

public class HibernateJavaConfigMain {

    public static void main(String[] args) {
        Player emp = new Player();
        emp.setName("Lisa");
//        emp.setRole("Manager");
//        emp.setInsertTime(new Date(System.currentTimeMillis()));

        //Get Session
//        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
//        Session session = sessionFactory.getCurrentSession();
        //start transaction
//        session.beginTransaction();
//        //Save the Model object
//        session.persist(emp);
//        //Commit transaction
//        session.getTransaction().commit();
//        System.out.println("Employee ID="+emp.getId());
//
//        //terminate session factory, otherwise program won't end
//        sessionFactory.close();
    }

}