package jpa_spring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpa.dao.UserDao;
import com.jpa.entity.User;

public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
		List<User> users = userDao.findAll();
		
		System.out.println(users);
	}

}
