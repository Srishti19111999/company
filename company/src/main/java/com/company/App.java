package com.company;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Scanner;
import java.util.List;
import com.company.utils.Util;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        char ch;
        do {
            System.out.println("~~~~~~~~~ Employee Deatils ~~~~~~~~~");
            System.out.println(
                    "1.Add\n2.Update\n3.Delete\n4.Display\n5.sort based on id\n6.Get the data with range\n7.Exit\nEnter your choice(1-7)");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    add();
                    break;
                }
                case 2: {
                    update();
                    break;
                }
                case 3: {
                    delete();
                    break;
                }
                case 4: {
                    display();
                    break;
                }
                case 5: {
                    sort();
                    break;
                }
                case 6: {
                    rangeData();
                    break;
                }
                case 7: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Wrong Choice");
                    break;
                }
            }
            System.out.println("Do you want to continue? (Y/N)");
            ch = sc.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');

    }

    public static void add() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("~~~~ Add ~~~~");

            Employee employee = new Employee();

            System.out.println("Enter employee name");
            String name = sc.next();
            System.out.println("Enter employee age");
            int age = sc.nextInt();

            employee.setName(name);
            employee.setAge(age);

            session.save(employee);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            System.out.println("Employee Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("~~~~ Update ~~~~");
            System.out.println("Enter the id for update");
            int id = sc.nextInt();
            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                System.out.println("Enter\t 1. To update name\t2. To update age\t3. To update both");
                int upChoice = sc.nextInt();
                switch (upChoice) {
                    case 1: {
                        System.out.println("Enter employee name");
                        String name = sc.next();
                        employee.setName(name);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter employee age");
                        int age = sc.nextInt();
                        employee.setAge(age);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter employee name");
                        String name = sc.next();
                        System.out.println("Enter employee age");
                        int age = sc.nextInt();
                        employee.setAge(age);
                        employee.setName(name);
                        break;
                    }
                    default:
                        System.out.println("wrong choice");
                }
                session.update(employee);
                Transaction transaction = session.beginTransaction();
                transaction.commit();
                System.out.println("Employee Details Updated Successfully");

            } else {
                System.out.println("No record is not exists with id = " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("~~~~ Delete ~~~~");

            System.out.println("Enter the id to delete");
            int id = sc.nextInt();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                Transaction transaction = session.beginTransaction();
                transaction.commit();
                System.out.println("Employee Deleted Successfully");
            } else {
                System.out.println("No record is not exists with id = " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("~~~~ Display ~~~~");
            System.out.println("Enter\t 1. To display single record\t2. To display all record");
            int disChoice = sc.nextInt();
            switch (disChoice) {
                case 1: {
                    System.out.println("Enter the id to display");
                    int id = sc.nextInt();
                    Employee employee = session.get(Employee.class, id);
                    if (employee != null) {
                        System.out.println("Id : " + employee.getId());
                        System.out.println("Name : " + employee.getName());
                        System.out.println("Age : " + employee.getAge());
                        System.out.println("Displayed Employee Details");
                    } else {
                        System.out.println("No record is not exists with id = " + id);
                    }
                    break;
                }
                case 2: {
                    Criteria criteria = session.createCriteria(Employee.class);
                    List<Employee> empList = criteria.list();
                    for (Employee emp : empList) {
                        System.out.println("ID : " + emp.getId() + "\tName : " + emp.getName() + "\tAge : "
                                + emp.getAge());
                    }
                    break;
                }
                default: {
                    System.out.println("Wrong Choice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sort() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("Enter\t1.Ascending order\t2.Decending order");
            int odrChoice = sc.nextInt();
            switch (odrChoice) {
                case 1: {
                    Criteria criteria = session.createCriteria(Employee.class);
                    criteria.addOrder(Order.asc("id"));
                    List<Employee> empList = criteria.list();
                    for (Employee emp : empList) {
                        System.out.println("ID : " + emp.getId() + "\tName : " + emp.getName() + "\tAge : "
                                + emp.getAge());
                    }
                    break;
                }
                case 2: {
                    Criteria criteria = session.createCriteria(Employee.class);
                    criteria.addOrder(Order.desc("id"));
                    List<Employee> empList = criteria.list();
                    for (Employee emp : empList) {
                        System.out.println("ID : " + emp.getId() + "\tName : " + emp.getName() + "\tAge : "
                                + emp.getAge());
                    }
                    break;
                }
                default: {
                    System.out.println("Wrong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rangeData() {
        Scanner sc = new Scanner(System.in);
        Session session = Util.getSession();
        try {
            System.out.println("Enter Range");
            int from = sc.nextInt();
            int to = sc.nextInt();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.between("id", from, to));

            List<Employee> empList = criteria.list();

            for (Employee emp : empList) {
                System.out.println(
                        "ID : " + emp.getId() + "\tName : " + emp.getName() + "\tAge : " + emp.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
