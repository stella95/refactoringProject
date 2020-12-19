package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import inputManagePackage.InputSystem;
import outputManagePackage.CreateCharts;

public class CreateChartsTest {
	
	private static CreateCharts createCharts;
	private static InputSystem inputSystem;
	private static ArrayList<String> taxpayersInfoFiles;
	private DefaultPieDataset receiptPieChartDataset;
	private JFreeChart receiptPieJFreeChart;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		taxpayersInfoFiles = new ArrayList<String>();
		taxpayersInfoFiles.add("testinput.txt");
		inputSystem =InputSystem.getInstance();;
		createCharts=CreateCharts.getInstance();
		inputSystem.addTaxpayersDataFromFilesIntoDatabase("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testInputFile", taxpayersInfoFiles);
	}

	@Before
	public void setUp() throws Exception {
		receiptPieChartDataset = new DefaultPieDataset();
	}

	@Test
	public void testCreatePieChart() {
		createCharts.createTaxpayerReceiptsPieJFreeChart(0);
		receiptPieJFreeChart=createCharts.getReceiptPieJFreeChart();
		receiptPieChartDataset=createCharts.getReceiptPieChartDataset();
		assertEquals(receiptPieJFreeChart.getTitle().getText(), "Receipt Pie Chart");
		assertEquals(receiptPieChartDataset.getValue("Basic").doubleValue(), 2000, 0.001);
		assertEquals(receiptPieChartDataset.getValue("Entertainment").doubleValue(), 0, 0.001);
		assertEquals(receiptPieChartDataset.getValue("Travel").doubleValue(), 0, 0.001);
		assertEquals(receiptPieChartDataset.getValue("Health").doubleValue(), 0, 0.001);
		assertEquals(receiptPieChartDataset.getValue("Other").doubleValue(), 0, 0.001);

	}

}
