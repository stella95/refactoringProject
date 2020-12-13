package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Receipt.Company;
import dataManagePackage.Receipt.Receipt;

public class ReceiptTest {
	private Receipt receipt;
	private Company company;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		receipt=new Receipt("Basic", "3", "25/2/2014", "1400.0", "Zara", "Greece", "Ioannina" , "Trikoupi", "20");
	}

	@Test
	public void test() {
		company=receipt.getCompany();
		assertEquals(receipt.getKind(), "Basic");
		assertEquals(receipt.getId(), "3");
		assertEquals(receipt.getDate(), "25/2/2014");
		assertEquals(receipt.getAmount(), 1400, 0.001);
		assertEquals(company.getName(), "Zara");
		assertEquals(company.getCountry(), "Greece");
		assertEquals(company.getCity(), "Ioannina");
		assertEquals(company.getStreet(), "Trikoupi");
		assertEquals(company.getNumber(), "20");
	}

}
