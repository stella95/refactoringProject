package createTagsMaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapMarriedSeperately implements ITagsMaps{
	private HashMap<String,ArrayList<String>> marriedSMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> marriedSList;
	private String line = "";
	
	public HashMap<String,ArrayList<String>> loadTagsForMap(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\marriedSeperalety.txt"))) {
    
            while ((line = br.readLine()) != null) {

                String[] lineOfFields = line.split(",");
                marriedSList = new ArrayList<String>();
                
                for(int i=1;i<6;i++) {
                	marriedSList.add(lineOfFields[i]);
                }
                marriedSMap.put(lineOfFields[0], marriedSList);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return marriedSMap;
	}
}
