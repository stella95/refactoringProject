package inputManagePackage;
import dataManagePackage.*;
import dataManagePackage.Receipt.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import createTagsMaps.TagsMaps;

public class InputSystem {
	
	private ReceiptFactory receiptFactory;
	private static InputSystem inputInstance = null;
	private HashMap<String,ArrayList<String>> receiptTagsMap = new HashMap<String,ArrayList<String>>();
	private TagsMaps tagsMap = TagsMaps.getInstance();
	private String typeFile;
	private Database database = Database.getInstance();
	
	public InputSystem(){}
	
	public static InputSystem getInstance() {
		if(inputInstance == null)
			inputInstance = new InputSystem();
		return inputInstance;
	}
	
	public void addTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		for (String afmInfoFile : taxpayersAfmInfoFiles)
		{
			if (afmInfoFile.endsWith(".txt")){
				receiptTagsMap=tagsMap.getTxtMap();
				typeFile="txt";
			}
			else if (afmInfoFile.endsWith(".xml")){
				receiptTagsMap=tagsMap.getXmlMap();
				typeFile="xml";
			}
			
			Scanner inputStream = null;
			try
			{
				inputStream = new Scanner(new FileInputStream(afmInfoFilesFolderPath+"\\"+afmInfoFile));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Problem opening " + afmInfoFile + " file.");
				System.exit(0);
			}			
			
			String taxpayerName = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("name")).get(0), (receiptTagsMap.get("name")).get(1));
			String taxpayerAFM = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("afm")).get(0), (receiptTagsMap.get("afm")).get(1));
			String taxpayerStatus = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("status")).get(0), (receiptTagsMap.get("status")).get(1));
			String taxpayerIncome = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("income")).get(0), (receiptTagsMap.get("income")).get(1));
			Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
			
			String fileLine;
			while (inputStream.hasNextLine())
			{
				fileLine = inputStream.nextLine();
				if (fileLine.equals("")) continue;
				if (fileLine.indexOf((receiptTagsMap.get("receipts")).get(0))!=-1) continue;
				if (typeFile=="xml") {
					if (fileLine.indexOf((receiptTagsMap.get("receipts")).get(1))!=-1) break;
				}
				
				String receiptID = getParameterValueFromTxtFileLine(fileLine, (receiptTagsMap.get("receiptId")).get(0), (receiptTagsMap.get("receiptId")).get(1));
				String receiptDate = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("date")).get(0), (receiptTagsMap.get("date")).get(1));
				String receiptKind = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("kind")).get(0), (receiptTagsMap.get("kind")).get(1));
				String receiptAmount = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("amount")).get(0), (receiptTagsMap.get("amount")).get(1));
				String receiptCompany = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("company")).get(0), (receiptTagsMap.get("company")).get(1));
				String receiptCountry = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("country")).get(0), (receiptTagsMap.get("country")).get(1));
				String receiptCity = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("city")).get(0), (receiptTagsMap.get("city")).get(1));
				String receiptStreet = getParameterValueFromTxtFileLine(inputStream.nextLine(),(receiptTagsMap.get("street")).get(0), (receiptTagsMap.get("street")).get(1));
				String receiptNumber = getParameterValueFromTxtFileLine(inputStream.nextLine(), (receiptTagsMap.get("number")).get(0), (receiptTagsMap.get("number")).get(1));
				Receipt newReceipt = receiptFactory.createNewReceipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
				
				newTaxpayer.addReceiptToList(newReceipt);
			}
			
			database.addTaxpayerToList(newTaxpayer);
		}
	}

	
	private String getParameterValueFromTxtFileLine(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
}