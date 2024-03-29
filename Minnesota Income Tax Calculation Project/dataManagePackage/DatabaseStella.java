package dataManagePackage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


import inputManagePackage.InputSystem;
import outputManagePackage.OutputSystem;

public class DatabaseStella {
	
	private static DatabaseStella databaseInstance = null;
	private String taxpayersInfoFilesPath;
	private ArrayList<Taxpayer> taxpayersArrayList = new ArrayList<Taxpayer>();
	private InputSystem inputSystem = InputSystem.getInstance();
	
	public DatabaseStella() {}
	
	public static DatabaseStella getInstance() {
		if(databaseInstance == null)
			databaseInstance = new DatabaseStella();
		return databaseInstance;
	}
	
	public void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		this.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	public String getTaxpayersInfoFilesPath(){
		return taxpayersInfoFilesPath;
	}
	
	public void proccessTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		inputSystem.addTaxpayersDataFromFilesIntoDatabase(afmInfoFilesFolderPath, taxpayersAfmInfoFiles);
	}
	
	public void addTaxpayerToList(Taxpayer taxpayer){
		taxpayersArrayList.add(taxpayer);
	}
	
	public int getTaxpayersArrayListSize(){
		return taxpayersArrayList.size();
	}
	
	public Taxpayer getTaxpayerFromArrayList(int index){
		return taxpayersArrayList.get(index);
	}
	
	public void removeTaxpayerFromArrayList(int index){
		taxpayersArrayList.remove(index);
	}
	
	public String getTaxpayerNameAfmValuesPairList(int index){
		Taxpayer taxpayer = taxpayersArrayList.get(index);
		return taxpayer.getName() + " | " + taxpayer.getAFM();
	}
	
	public String[] getTaxpayersNameAfmValuesPairList(){
		String[] taxpayersNameAfmValuesPairList = new String[taxpayersArrayList.size()];
		
		int c = 0;
		for (Taxpayer taxpayer : taxpayersArrayList){
			taxpayersNameAfmValuesPairList[c++] = taxpayer.getName() + " | " + taxpayer.getAFM();
		}
		
		return taxpayersNameAfmValuesPairList;
	}
	
	public void updateTaxpayerInputFile(int index){
		File taxpayersInfoFilesPathFileObject = new File(taxpayersInfoFilesPath);
		FilenameFilter fileNameFilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
               return (name.toLowerCase().endsWith("_info.txt") || name.toLowerCase().endsWith("_info.xml"));
            }
         };
		
		for (File file : taxpayersInfoFilesPathFileObject.listFiles(fileNameFilter)){
			if (!file.getName().contains(taxpayersArrayList.get(index).getAFM())) continue;
			
			if (file.getName().toLowerCase().endsWith(".txt")){
				OutputSystem.saveUpdatedTaxpayerTxtInputFile(file.getAbsolutePath(), index);
			}
			if (file.getName().toLowerCase().endsWith(".xml")){
				OutputSystem.saveUpdatedTaxpayerXmlInputFile(file.getAbsolutePath(), index);
			}
			break;
		}
	}	
}
