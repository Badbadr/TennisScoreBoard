package org.example.edu;

import org.example.repository.config.HibernateConfig;
import org.hibernate.Session;

public class HibernateXmlMain {

    public static void main(String[] args) {
//        Employee emp = new Employee();
//        emp.setName("Pankaj");
//        emp.setRole("CEO");
//        emp.setInsertTime(new Date(System.currentTimeMillis()));
        //Get Session
        Session session = HibernateConfig.getSessionFactory().openSession();
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