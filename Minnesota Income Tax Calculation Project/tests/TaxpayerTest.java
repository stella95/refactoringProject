package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Taxpayer;

public class TaxpayerTest {
	
	private Taxpayer taxpayer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		taxpayer = new Taxpayer("Apostolos Zarras", "123456", "Single", "2000");
	}

	@Test
	public void testTaxpayer() {
		assertEquals(taxpayer.getName(), "Apostolos Zarras");
		assertEquals(taxpayer.getAFM(), "123456");
		assertEquals(taxpayer.getFamilyStatus(), "Single");
		assertEquals(taxpayer.getIncome(), 2000, 0.001);
		assertEquals(taxpayer.getBasicTax(), 107, 0.001);
		assertEquals(taxpayer.getTaxInxrease(), 0, 0.001);
		assertEquals(taxpayer.getTaxDecrease(), 0, 0.001);
		assertEquals(taxpayer.getTotalTax(), 107, 0.001);
	}

}
