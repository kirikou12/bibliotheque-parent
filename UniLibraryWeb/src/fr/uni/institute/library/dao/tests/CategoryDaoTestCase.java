package fr.uni.institute.library.dao.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.dao.CategoryDao;
import fr.uni.institute.library.dao.DaoException;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;

public class CategoryDaoTestCase {
	
	private static long resultatAttendu;
	private static CategoryDao categoryDao;
	private static Connection connection;

	@Before
	public void setUp() throws Exception {
		System.out.println("Définition des conditions initiales");
		resultatAttendu = 10;
		System.out.println("Initialisation de la connexion ");
		Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/uni_library_db", "root", "");
		categoryDao = new CategoryDaoJdbc(connection);
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Libération des resources");
		categoryDao = null;
		connection.close();
		connection = null;
	}

	@Test
	public void testResearchAllCategories() {
		System.out.println("Récupération de la liste ");
		try {
			Collection<Category> liste = categoryDao.researchAllCategories();
			assertNotNull(liste);
			assertEquals(resultatAttendu, liste.size(),0);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		
	}

}
