package org.example.edu;

import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;

public class HibernateXmlMain {

    public static void main(String[] args) {
//        Employee emp = new Employee();
//        emp.setName("Pankaj");
//        emp.setRole("CEO");
//        emp.setInsertTime(new Date(System.currentTimeMillis()));
        //Get Session
        Session session = HibernateUtil.getSessionFactory().openSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
//        session.persist(emp);
        //Commit transaction
        session.getTransaction().commit();
//        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        session.close();
    }

}