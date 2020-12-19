package createTagsMaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapSingle implements ITagsMaps{
	private HashMap<String,ArrayList<String>> singleMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> singleList;
	private String line = "";
	
	public HashMap<String,ArrayList<String>> loadTagsForMap(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\single.txt"))) {
    
            while ((line = br.readLine()) != null) {

                String[] lineOfFields = line.split(",");
                singleList = new ArrayList<String>();
                
                for(int i=1;i<6;i++) {
                	singleList.add(lineOfFields[i]);
                }
                singleMap.put(lineOfFields[0], singleList);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return singleMap;
	}
}
