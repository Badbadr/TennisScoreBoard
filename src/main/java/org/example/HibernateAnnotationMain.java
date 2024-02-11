package org.example;

import org.example.model.Player;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

public class HibernateAnnotationMain {

    public static void main(String[] args) {
        Player emp = new Player();
        emp.setName("David");
//        emp.setRole("Developer");
//        emp.setInsertTime(new Date(System.currentTimeMillis()));
//        System.out.println(emp.getId());
//        //Get Session
//        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
//        Session session = sessionFactory.getCurrentSession();
//        //start transaction
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