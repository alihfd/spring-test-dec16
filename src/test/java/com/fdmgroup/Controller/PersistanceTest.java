package com.fdmgroup.Controller;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistanceTest {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private TestEntityManager em;

//	@Before
//	public void setUp() {
//		List<User> list = em.getEntityManager().createQuery("from User").getResultList();
//		for(User user:list) {
//			em.remove(user);
//		}
//	}
	

	@Test
	public void testFindById() {
		User user1 = new User("Ali", "H", "ali@gmail.com", "alihf", "password1");
		em.persist(user1);
		em.flush();
		//Optional<User> found = userDao.findById(1);
		List<User> userList = userDao.findAll();
		Assert.assertEquals(userList.get(0).getFirstName(), user1.getFirstName());
		Assert.assertEquals(userList.get(0).getLastName(), user1.getLastName());
		Assert.assertEquals(userList.get(0), user1);
		Assert.assertTrue(user1.getFirstName().equals(userList.get(0).getFirstName()) && user1.getLastName().equals(userList.get(0).getLastName()));

	
	}
	
	@Test
	public void testGetUserByUsernameAndPassword() {
		User user2 = new User("blah", "H", "blahh@gmail.com", "blahh", "password2");
		em.persist(user2);
		em.flush();
		
		User found = userDao.getUser(user2.getUserName(), user2.getPassword());
		Assert.assertEquals(found.getFirstName(), user2.getFirstName());
	}
}
