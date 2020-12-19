package dataManagePackage;
import dataManagePackage.Receipt.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Taxpayer {
	private String name;
	private String afm;
	private double income;
	private double basicTax;
	private double taxIncrease;
	private double taxDecrease;
	private double totalTax;
	private ArrayList<Receipt> receipts;
	private HashMap<String,ArrayList<String>> familyStatusMap;
	private FamilyStatus setFamilyStatus;
	
	public Taxpayer(String name, String afm, String familyStatus, String income){
		this.name = name;
		this.afm = afm;
		this.income = Double.parseDouble(income);
		setFamilyStatus = new FamilyStatus(familyStatus);
		familyStatusMap=setFamilyStatus.setMapForFamilyStatus();
		calculateTaxTaxpayer(this.income);
		taxIncrease = 0;
		taxDecrease = 0;
		
		receipts = new ArrayList<Receipt>();
	}
	
	public void calculateTaxTaxpayer(double totalIncome){
		
		if (totalIncome < Integer.parseInt(familyStatusMap.get("incomelimits").get(1))){
			basicTax = (Double.parseDouble(familyStatusMap.get("taxRates").get(0))/100) * totalIncome;
		}
		else if (totalIncome < Integer.parseInt(familyStatusMap.get("incomelimits").get(2))){
			basicTax = Double.parseDouble(familyStatusMap.get("incomeTaxRates").get(1)) + ((Double.parseDouble(familyStatusMap.get("taxRates").get(1))/100) * (totalIncome-Integer.parseInt(familyStatusMap.get("incomelimits").get(1))));
		}
		else if (totalIncome < Integer.parseInt(familyStatusMap.get("incomelimits").get(3))){
			basicTax = Double.parseDouble(familyStatusMap.get("incomeTaxRates").get(2)) + ((Double.parseDouble(familyStatusMap.get("taxRates").get(2))/100) * (totalIncome-Integer.parseInt(familyStatusMap.get("incomelimits").get(2))));
		}
		else if (totalIncome < Integer.parseInt(familyStatusMap.get("incomelimits").get(4))){
			basicTax = Double.parseDouble(familyStatusMap.get("incomeTaxRates").get(3)) + ((Double.parseDouble(familyStatusMap.get("taxRates").get(3))/100) * (totalIncome-Integer.parseInt(familyStatusMap.get("incomelimits").get(3))));
		}
		else{
			basicTax = Double.parseDouble(familyStatusMap.get("incomeTaxRates").get(4)) + ((Double.parseDouble(familyStatusMap.get("taxRates").get(4))/100) * (totalIncome-Integer.parseInt(familyStatusMap.get("incomelimits").get(4))));
		}
		
		totalTax = basicTax;
	}
	
	
	public String toString(){
		return "Name: "+name
				+"\nAFM: "+afm
				+"\nStatus: "+getFamilyStatus()
				+"\nIncome: "+String.format("%.2f", income)
				+"\nBasicTax: "+String.format("%.2f", basicTax)
				+"\nTaxIncrease: "+String.format("%.2f", taxIncrease)
				+"\nTaxDecrease: "+String.format("%.2f", taxDecrease);
	}
	
	public Receipt getReceipt(int receiptID){
		return receipts.get(receiptID);
	}
	
	public ArrayList<Receipt> getReceiptsArrayList(){
		return receipts;
	}
	
	public String[] getReceiptsList(){
		String[] receiptsList = new String[receipts.size()];
		
		int c = 0;
		for (Receipt receipt : receipts){
			receiptsList[c++] = receipt.getId() + " | " + receipt.getDate() + " | " + receipt.getAmount();
		}
		
		return receiptsList;
	}
	
	public double getReceiptsTotalAmount(String kind){
		double basicReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals(kind)){
				basicReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(basicReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	
	public double getTotalReceiptsAmount(){
		double totalReceiptsAmount = 0;
		
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		return (new BigDecimal(totalReceiptsAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public String getName(){
		return name;
	}
	
	public String getAFM(){
		return afm;
	}
	
	public String getFamilyStatus(){
		return setFamilyStatus.getFamilyStatus();
	}
	
	public double getIncome(){
		return (new BigDecimal(income).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getBasicTax(){
		return (new BigDecimal(basicTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxInxrease(){
		return (new BigDecimal(taxIncrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxDecrease(){
		return (new BigDecimal(taxDecrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTotalTax(){
		return (new BigDecimal(totalTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public void addReceiptToList(Receipt receipt){
		receipts.add(receipt);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void removeReceiptFromList(int index){
		receipts.remove(index);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts(){
		double totalReceiptsAmount = 0;
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		taxIncrease = 0;
		taxDecrease = 0;
		if ((totalReceiptsAmount/(double)income) < 0.2){
			taxIncrease = basicTax * 0.08;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.4){
			taxIncrease = basicTax * 0.04;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.6){
			taxDecrease = basicTax * 0.15;
		}
		else{
			taxDecrease = basicTax * 0.30;
		}
		
		totalTax = basicTax + taxIncrease - taxDecrease;
	}
}