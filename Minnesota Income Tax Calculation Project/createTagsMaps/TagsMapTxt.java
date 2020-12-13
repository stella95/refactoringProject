package createTagsMaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TagsMapTxt implements ITagsMaps{
	private HashMap<String,ArrayList<String>> receiptTagsMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> receiptTagsList;
	private String line = "";
	
	public HashMap<String,ArrayList<String>> loadTagsForMap(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\tagsTxt.txt"))) {
    
            while ((line = br.readLine()) != null) {

                String[] lineOfFields = line.split(","); 
                receiptTagsList = new ArrayList<String>();
                
                receiptTagsList.add(lineOfFields[1]);
            	receiptTagsList.add("");
            	receiptTagsMap.put(lineOfFields[0], receiptTagsList);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return receiptTagsMap;
	}

}
