package createTagsMaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapHeadOfHousehold implements ITagsMaps{
	private HashMap<String,ArrayList<String>> headMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> headList;
	private String line = "";
	
	public HashMap<String,ArrayList<String>> loadTagsForMap(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\headOfHousehold.txt"))) {
    
            while ((line = br.readLine()) != null) {

                String[] lineOfFields = line.split(",");
                headList = new ArrayList<String>();
                
                for(int i=1;i<6;i++) {
                	headList.add(lineOfFields[i]);
                }
                headMap.put(lineOfFields[0], headList);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return headMap;
	}
}
