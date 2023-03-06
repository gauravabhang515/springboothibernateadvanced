package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean falg = false;

        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {

                falg = true;

            }
        }
        return falg;
    }

    @Override
    public Employee getDataById(int empId) {
        Session session = factory.openSession();


        return (Employee) session.get(Employee.class, empId);
    }

    @Override
    public void saveBulkOfData(List<Employee> employees) {

        Session session = factory.openSession();

        for (Employee employee : employees) {
            Transaction transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        }

    }

    @Override
    public List<Employee> getDataByUsingAnyInput(String input)  {





        List<Employee> empList = new ArrayList<>();

        for (Employee employee : getAllData()) {

            String dob;

            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

            dob=dateFormat.format(employee.getEmpDOB());



            if (employee.getEmpName().equals(input)
                    || (String.valueOf(employee.getEmpId()).equals(input))
                    || (String.valueOf(employee.getEmpContactNumber()).equals(input))
                    || dob.equals(input)) {
                empList.add(employee);

            }
        }
        return empList;

    }

    @Override
    public List<Employee> getAllData() {

        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public void updateData(int empId, Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        Employee employee1 = getDataById(empId);


        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        session.update(employee1);
        transaction.commit();
    }

    @Override
    public void deleteDataById(int empId) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = getDataById(empId);

        session.update(employee);
        transaction.commit();

    }

    @Override
    public void deleteAllData() {

        Session session = factory.openSession();
        for (Employee employee : getAllData()) {

            Transaction transaction = session.beginTransaction();

            session.delete(employee);
            transaction.commit();
        }

    }
}
