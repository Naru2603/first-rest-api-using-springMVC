package com.infosys.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infosys.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveStudent(Student std) {
		
		boolean result = false;
		try {
			Session session = sessionFactory.openSession();

			Transaction txn = session.beginTransaction();
			session.save(std);
			txn.commit();
			session.close();
			result = true;
		} catch (Exception e) {
			System.out.println("Exception occuered while storing student : " + e.getMessage());
		}

		return result;
	}

	@Override
	public Student getStudent(int id) {
		
		Student std = null;
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		std = session.get(Student.class, id);
		
		txn.commit();
		session.close();
		
		return std;
	}

	@Override
	public List<Student> getStudents() {
		
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		
		List<Student> stdList = null;
		try {
			txn = session.beginTransaction();
			Query<Student> query = session.createQuery("from Student");
			stdList =query.list();
			txn.commit();
		}
		catch(HibernateException ex) {
			if(txn != null) {
				txn.rollback();
			}
			System.out.println("Exception : "+ ex.getMessage());
			ex.printStackTrace(System.err);
		}
		finally {
			session.close();
			return stdList;
		}
	}

	@Override
	public boolean deleteStudent(int id) {
		
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		boolean res = false;
		Student retrievedStudent = null;
		try {
			txn = session.beginTransaction();
			
			retrievedStudent = session.get(Student.class, id);
			
			if(retrievedStudent != null) {
				session.delete(retrievedStudent);
				res = true;
			}
			
			txn.commit();
		}
		catch(HibernateException ex) {
			System.out.println("Unable to delete student..!! "+ ex.getMessage());
		}
		return res;
	}

	@Override
	public Student updateStudent(Student std) {
		
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		Student retrievedStudent = null;
		try {
			txn = session.beginTransaction();
			
			retrievedStudent = session.get(Student.class, std.getId());
			
			if(retrievedStudent != null) {
				System.out.println("Found the existing student..Updating it..!!");
				retrievedStudent.setRoll(std.getRoll());
				retrievedStudent.setName(std.getName());
				retrievedStudent.setAddress(std.getAddress());

				session.update(retrievedStudent);
				
				System.out.println("Student updated successfully..!!");
			}
			else {
				System.out.println("Since Student was not present creating new Student");
				session.save(std);
			}
			txn.commit();
		}
		catch(HibernateException ex) {
			System.out.println("Unable to Update student..!! "+ ex.getMessage());
		}
		return retrievedStudent; 
	}

	@Override
	public List<Student> getStudents(Integer firstResult, Integer maxResult) {
		List<Student> students = new ArrayList<>();
		int paginatedCount = 0;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("From Student");
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			students = (List) query.list();
			if(students != null) {
				paginatedCount = students.size();
				System.out.println("total results : "+ paginatedCount);
				for (Student std :students) {
					System.out.println("Retreived Student using Query : "+ std);
				}
			}
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return students;
			}
}

