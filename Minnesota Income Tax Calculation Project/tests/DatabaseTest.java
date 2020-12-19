package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class DatabaseTest {
	
	private Database database;
	private Taxpayer taxpayer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		database=Database.getInstance();
		taxpayer = new Taxpayer("Apostolos Zarras", "123456", "Single", "2000");
		database.addTaxpayerToList(taxpayer);
	}
	

	@Test
	public void testDatabase() {
		assertEquals(database.getTaxpayersArrayListSize(), 1);
		
		Taxpayer taxpayer2 = database.getTaxpayerFromArrayList(0);
		assertEquals(taxpayer2.getName(), "Apostolos Zarras");
		
		database.removeTaxpayerFromArrayList(0);
		assertEquals(database.getTaxpayersArrayListSize(), 0);
	}


}
