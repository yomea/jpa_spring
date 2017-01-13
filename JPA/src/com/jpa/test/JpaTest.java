package com.jpa.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.entity.Group;
import com.jpa.entity.Person;
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
	public void savePerson() {
		em.getTransaction().begin();
		try {
			Person person = new Person();
			person.setUsername("username");
			person.setBirthday(new Date());
			/*InputStream in = this.getClass().getResourceAsStream("/1.jpg");
			byte[] buff = new byte[in.available()];
			in.read(buff);
			person.setPhoto(buff);*/
			em.persist(person);
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		em.getTransaction().commit();
	}
	
	@Test
	public void findPerson_1() {
		
		Person person = em.find(Person.class, 13);
			
		System.out.println(person);
	}
	
	@Test
	public void findPerson_2() {
		
		Person person = em.getReference(Person.class, 13);//类似hibernate的load延迟加载
			
		System.out.println(person);
	}
	
	@Test
	public void updatePerson_1() {
		
		em.getTransaction().begin();
		
		try {
			Person person = em.find(Person.class, 13);
			
			person.setUsername("yes");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	/**
	 * Hibernate: select person0_.id as id1_0_0_, person0_.birthday as birthday2_0_0_, person0_.hah as hah3_0_0_, person0_.hehe as hehe4_0_0_, person0_.introduct as introduc5_0_0_, person0_.photo as photo6_0_0_, person0_.sex as sex7_0_0_, person0_.user_name as user_nam8_0_0_ from Person person0_ where person0_.id=?
	 * 使用merage时会调用一下sql语句
	 * Hibernate: select person0_.id as id1_0_0_, person0_.birthday as birthday2_0_0_, person0_.hah as hah3_0_0_, person0_.hehe as hehe4_0_0_, person0_.introduct as introduc5_0_0_, person0_.photo as photo6_0_0_, person0_.sex as sex7_0_0_, person0_.user_name as user_nam8_0_0_ from Person person0_ where person0_.id=?
	 * Hibernate: update Person set birthday=?, hah=?, hehe=?, introduct=?, photo=?, sex=?, user_name=? where id=?
	 */
	@Test
	public void updatePerson_2() {
		
		em.getTransaction().begin();
		
		try {
			Person person = em.find(Person.class, 13);
			
			em.clear();//清除缓存
			
			person.setUsername("No");//提交后将无效
			em.merge(person);//这才有用
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	
	@Test
	public void removePerson_1() {
		
		em.getTransaction().begin();
		
		try {
			Person person = em.find(Person.class, 13);
			
			em.remove(person);
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	@Test
	public void queryPerson_1() {
		
		em.getTransaction().begin();
		
		try {
			javax.persistence.Query query = em.createQuery("select o from Person o where o.id=?0");
			query.setParameter(0, 14);
			Person person = (Person) query.getSingleResult();
			System.out.println(person);
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	@Test
	public void queryPerson_2() {
		
		em.getTransaction().begin();
		
		try {
			javax.persistence.TypedQuery<Person> query = em.createQuery("select o from Person o where o.id=:id", Person.class);
		//	javax.persistence.Query query = em.createQuery("select o from Person o where o.id=:id", Person.class);
			query.setParameter("id", 14);
		//	Person person = query.getSingleResult();
			//Person person = (Person) query.getSingleResult();
			//System.out.println(person);
			java.util.List<Person> persons = query.getResultList();
			
			for (Person person2 : persons) {
				System.out.println(person2);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	@Test
	public void queryPerson_3() {
		
		em.getTransaction().begin();
		
		try {
			//创建条件创建者，建造者模式
			CriteriaBuilder cb = em.getCriteriaBuilder();
			//创建条件查询
			CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
			javax.persistence.criteria.Root<Person> root = criteriaQuery.from(Person.class);
			criteriaQuery.select(root);
			
			
			ParameterExpression<String> pe_title = cb.parameter(String.class,"username");  
	          
			criteriaQuery.where(cb.equal(root.get("username"), pe_title));  
	          
	          
	        TypedQuery<Person> q = em.createQuery(criteriaQuery);  
	        q.setParameter("username", "username"); 
	        for (Person p : q.getResultList()) {
				System.out.println(p);
			}
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		
	}
	
	@Test
	public void save_one2many_1() {
		em.getTransaction().begin();
		Group group = new Group();
		group.setLevel(5);
		for(int i = 0; i < 10; i++) {
			
			group.getStudents().add(new Student(null, "a" + i, group));
			
		}
		em.persist(group);
		em.getTransaction().commit();
	}
	
	//@OneToMany(fetch=FetchType.LAZY)
	@Test
	public void query_one2many() {
		em.getTransaction().begin();
		com.jpa.entity.Group group = em.find(Group.class, 4);
		em.getTransaction().commit();
	}
	
	
	@Test
	public void update_one2many() {
		em.getTransaction().begin();
		com.jpa.entity.Group group = em.find(Group.class, 4);
		group.setLevel(2);
		em.getTransaction().commit();
	}
	
	
	@Test
	public void update_many2one() {
		em.getTransaction().begin();
		com.jpa.entity.Student student = em.find(Student.class, 20);
		student.setName("妹妹");
		em.getTransaction().commit();
	}
	
	
	@Test
	public void save_many2one() {
		em.getTransaction().begin();
		Group group = new Group();
		group.setLevel(5);
		Student student = new Student(null, "yu", group);
		group.getStudents().add(student);
		//必须在student中设置级联操作，否自报错
		em.persist(student);
		em.getTransaction().commit();
	}
	
	@After
	public void closeEntityManager() {
		
		em.close();
		factory.close();
		
	}

}
