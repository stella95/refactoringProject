package createTagsMaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapMarriedJointly implements ITagsMaps{
	private HashMap<String,ArrayList<String>> marriedJMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> marriedJList;
	private String line = "";
	
	public HashMap<String,ArrayList<String>> loadTagsForMap(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\marriedJointly.txt"))) {
    
            while ((line = br.readLine()) != null) {

                String[] lineOfFields = line.split(",");
                marriedJList = new ArrayList<String>();
                
                for(int i=1;i<6;i++) {
                	marriedJList.add(lineOfFields[i]);
                }
                marriedJMap.put(lineOfFields[0], marriedJList);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return marriedJMap;
	}
}
