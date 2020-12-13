package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import createTagsMaps.TagsMaps;
import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;

public class SaveInputFile {
	
	private HashMap<String,ArrayList<String>> receiptTagsMap = new HashMap<String,ArrayList<String>>();
	private TagsMaps tagsMap = TagsMaps.getInstance();
	private Database database = Database.getInstance();
	
	public SaveInputFile() {}
	
	public void saveUpdatedTaxpayerInputFile(String filePath, int taxpayerIndex, String typeFile){
		
		if (typeFile=="txt"){
			receiptTagsMap=tagsMap.getTxtMap();
		}
		else if (typeFile=="xml"){
			receiptTagsMap=tagsMap.getXmlMap();
		}
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println((receiptTagsMap.get("name")).get(0) +taxpayer.getName()+(receiptTagsMap.get("name")).get(1));
		outputStream.println((receiptTagsMap.get("afm")).get(0)+taxpayer.getAFM()+(receiptTagsMap.get("afm")).get(1));
		outputStream.println((receiptTagsMap.get("status")).get(0)+taxpayer.getFamilyStatus()+(receiptTagsMap.get("status")).get(1));
		outputStream.println((receiptTagsMap.get("income")).get(0)+taxpayer.getIncome()+(receiptTagsMap.get("income")).get(1));
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println((receiptTagsMap.get("receipts")).get(0));
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println((receiptTagsMap.get("receiptsId")).get(0)+receipt.getId()+(receiptTagsMap.get("receiptsId")).get(1));
				outputStream.println((receiptTagsMap.get("date")).get(0)+receipt.getDate()+(receiptTagsMap.get("date")).get(1));
				outputStream.println((receiptTagsMap.get("kind")).get(0)+receipt.getKind()+(receiptTagsMap.get("kind")).get(1));
				outputStream.println((receiptTagsMap.get("amount")).get(0)+receipt.getAmount()+(receiptTagsMap.get("amount")).get(1));
				outputStream.println((receiptTagsMap.get("company")).get(0)+receipt.getCompany().getName()+(receiptTagsMap.get("company")).get(1));
				outputStream.println((receiptTagsMap.get("country")).get(0)+receipt.getCompany().getCountry()+(receiptTagsMap.get("country")).get(1));
				outputStream.println((receiptTagsMap.get("city")).get(0)+receipt.getCompany().getCity()+(receiptTagsMap.get("city")).get(1));
				outputStream.println((receiptTagsMap.get("street")).get(0)+receipt.getCompany().getStreet()+(receiptTagsMap.get("street")).get(1));
				outputStream.println((receiptTagsMap.get("number")).get(0)+receipt.getCompany().getNumber()+(receiptTagsMap.get("number")).get(1));
				outputStream.println();
			}
			
			outputStream.println((receiptTagsMap.get("receipts")).get(1));
		}
		
		outputStream.close();
	}
}
