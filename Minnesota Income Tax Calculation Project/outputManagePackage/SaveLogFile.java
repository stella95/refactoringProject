package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import createTagsMaps.TagsMaps;
import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class SaveLogFile {
	
	private static SaveLogFile logInstance = null;
	private HashMap<String,ArrayList<String>> receiptTagsMap = new HashMap<String,ArrayList<String>>();
	private TagsMaps tagsMap = TagsMaps.getInstance();
	private String logFileEnds;
	private Database database = Database.getInstance();
	
	public SaveLogFile() {}
	
	public static SaveLogFile getInstance() {
		if(logInstance == null)
			logInstance = new SaveLogFile();
		return logInstance;
	}
	
	public void saveTaxpayerInfoToLogFile(String folderSavePath, int taxpayerIndex, String typeFile){
		
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		if (typeFile=="txt"){
			receiptTagsMap=tagsMap.getTxtMap();
			logFileEnds="_LOG.txt";
		}
		else if (typeFile=="xml"){
			receiptTagsMap=tagsMap.getXmlMap();
			logFileEnds="_LOG.xml";
		}
		PrintWriter outputStream = null;
		
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+logFileEnds));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+logFileEnds);
		}
		
		outputStream.println((receiptTagsMap.get("name")).get(0)+taxpayer.getName()+(receiptTagsMap.get("name")).get(1));
		outputStream.println((receiptTagsMap.get("afm")).get(0)+taxpayer.getAFM()+(receiptTagsMap.get("afm")).get(1));
		outputStream.println((receiptTagsMap.get("income")).get(0)+taxpayer.getIncome()+(receiptTagsMap.get("income")).get(1));
		outputStream.println((receiptTagsMap.get("basicTax")).get(0)+taxpayer.getBasicTax()+(receiptTagsMap.get("basicTax")).get(1));
		if (taxpayer.getTaxInxrease()!=0){
			outputStream.println((receiptTagsMap.get("taxInc")).get(0)+taxpayer.getTaxInxrease()+(receiptTagsMap.get("taxInc")).get(1));
		}else{
			outputStream.println((receiptTagsMap.get("taxDec")).get(0)+taxpayer.getTaxDecrease()+(receiptTagsMap.get("taxDec")).get(1));
		}
		outputStream.println((receiptTagsMap.get("totalTax")).get(0)+taxpayer.getTotalTax()+(receiptTagsMap.get("totalTax")).get(1));
		outputStream.println((receiptTagsMap.get("totalRecAm")).get(0)+taxpayer.getTotalReceiptsAmount()+(receiptTagsMap.get("totalRecAm")).get(1));
		outputStream.println((receiptTagsMap.get("entertainment")).get(0)+taxpayer.getReceiptsTotalAmount("Entertainment")+(receiptTagsMap.get("entertainment")).get(1));
		outputStream.println((receiptTagsMap.get("basic")).get(0)+taxpayer.getReceiptsTotalAmount("Basic")+(receiptTagsMap.get("basic")).get(1));
		outputStream.println((receiptTagsMap.get("travel")).get(0)+taxpayer.getReceiptsTotalAmount("Travel")+(receiptTagsMap.get("travel")).get(1));
		outputStream.println((receiptTagsMap.get("health")).get(0)+taxpayer.getReceiptsTotalAmount("Health")+(receiptTagsMap.get("health")).get(1));
		outputStream.println((receiptTagsMap.get("other")).get(0)+taxpayer.getReceiptsTotalAmount("Other")+(receiptTagsMap.get("other")).get(1));
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	}
}
