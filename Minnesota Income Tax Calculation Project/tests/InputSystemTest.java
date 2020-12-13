package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Company;
import dataManagePackage.Receipt.Receipt;
import inputManagePackage.InputSystem;

public class InputSystemTest {
	
	private Taxpayer systemTaxpayer;
	private Receipt receipt;
	private Company company;
	private ArrayList<String> taxpayersInfoFiles;
	private InputSystem inputSystem;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		taxpayersInfoFiles = new ArrayList<String>();
		taxpayersInfoFiles.add("testinput.txt");
		inputSystem =InputSystem.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInputSystem() {
		//InputSystem.addTaxpayersDataFromFilesIntoDatabase("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files", taxpayersInfoFiles);
		inputSystem.addTaxpayersDataFromFilesIntoDatabase("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files", taxpayersInfoFiles);
		systemTaxpayer=Database.getTaxpayerFromArrayList(0);
		receipt=systemTaxpayer.getReceipt(0);
		company=receipt.getCompany();
		assertEquals(systemTaxpayer.getName(), "Apostolos Zarras");
		assertEquals(systemTaxpayer.getAFM(), "130456093");
		assertEquals(systemTaxpayer.getFamilyStatus(), "Married Filing Jointly");
		assertEquals(systemTaxpayer.getIncome(), 22570, 0.001);
		assertEquals(receipt.getId(), "1");
		assertEquals(receipt.getDate(), "25/2/2014");
		assertEquals(receipt.getKind(), "Basic");
		assertEquals(receipt.getAmount(), 2000, 0.001);
		assertEquals(company.getName(), "Hand Made Clothes");
		assertEquals(company.getCountry(), "Greece");
		assertEquals(company.getCity(), "Ioannina");
		assertEquals(company.getStreet(), "Kaloudi");
		assertEquals(company.getNumber(), "10");
	}

}
