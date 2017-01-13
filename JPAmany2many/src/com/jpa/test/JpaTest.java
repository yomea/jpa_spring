package com.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.entity.Student;

public class JpaTest {
	EntityManager em = null;
	EntityManagerFactory factory = null;
	
	@Before
	public void createEntityManager() {
		
		factory = Persistence.createEntityManagerFactory("test");
		em = factory.createEntityManager();
		
	}


	@Test
	public void test() {
		Query query = em.createNamedQuery("find student by id");
		
		query.setParameter(1, 2);
		
		java.util.List<Student> lists = query.getResultList();
	}
	
	@After
	public void closeEntityManager() {
		
		em.close();
		factory.close();
		
	}

}
