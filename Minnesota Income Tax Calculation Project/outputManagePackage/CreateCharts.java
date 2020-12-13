package outputManagePackage;

import java.awt.Dialog.ModalExclusionType;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class CreateCharts {
	
	private DefaultPieDataset receiptPieChartDataset;
	private JFreeChart receiptPieJFreeChart;
	private PiePlot piePlot;
	private Database database = Database.getInstance();
	
	public CreateCharts() {}
	
	public DefaultPieDataset getReceiptPieChartDataset() {
		return receiptPieChartDataset;
	}

	public JFreeChart getReceiptPieJFreeChart() {
		return receiptPieJFreeChart;
	}

	public PiePlot getPiePlot() {
		return piePlot;
	}

	public ChartFrame getReceiptPieChartFrame() {
		return receiptPieChartFrame;
	}

	private ChartFrame receiptPieChartFrame;
	
	public void createTaxpayerReceiptsPieJFreeChart(int taxpayerIndex){
		receiptPieChartDataset = new DefaultPieDataset();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		receiptPieChartDataset.setValue("Basic", taxpayer.getBasicReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Entertainment", taxpayer.getEntertainmentReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Travel", taxpayer.getTravelReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Health", taxpayer.getHealthReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Other", taxpayer.getOtherReceiptsTotalAmount());
		
		receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", receiptPieChartDataset);
		piePlot = (PiePlot)receiptPieJFreeChart.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator); 

		receiptPieChartFrame = new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
	
	public void createTaxpayerTaxAnalysisBarJFreeChart(int taxpayerIndex){
		DefaultCategoryDataset taxAnalysisBarChartDataset = new DefaultCategoryDataset();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		String taxVariationType = taxpayer.getTaxInxrease()!=0? "Tax Increase" : "Tax Decrease";
		double taxVariationAmount = taxpayer.getTaxInxrease()!=0? taxpayer.getTaxInxrease() : taxpayer.getTaxDecrease()*(-1);
		
		taxAnalysisBarChartDataset.setValue(taxpayer.getBasicTax(), "Tax", "Basic Tax");
		taxAnalysisBarChartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
		taxAnalysisBarChartDataset.setValue(taxpayer.getTotalTax(), "Tax", "Total Tax");

		JFreeChart taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", taxAnalysisBarChartDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartFrame receiptPieChartFrame = new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), taxAnalysisJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
}
