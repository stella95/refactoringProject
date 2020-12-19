package createTagsMaps;

import java.util.ArrayList;
import java.util.HashMap;

public class TagsMaps {
	
	private static TagsMaps tagMapInstance = null;
	private HashMap<String,ArrayList<String>> receiptTagsMapTxt = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> receiptTagsMapXml = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> singleMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> marriedJMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> marriedSMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> headMap = new HashMap<String,ArrayList<String>>();
	private ITagsMaps tagsMaps;
	private TagsMapsFactory tagsMapsFactory = new TagsMapsFactory();
	
	public TagsMaps(){
		tagsMaps = tagsMapsFactory.createTagsMap("txt");
		receiptTagsMapTxt = tagsMaps.loadTagsForMap();
		
		tagsMaps = tagsMapsFactory.createTagsMap("xml");
		receiptTagsMapXml = tagsMaps.loadTagsForMap();
		
		tagsMaps = tagsMapsFactory.createTagsMap("single");
		singleMap = tagsMaps.loadTagsForMap();
		
		tagsMaps = tagsMapsFactory.createTagsMap("marriedJointly");
		marriedJMap = tagsMaps.loadTagsForMap();
		
		tagsMaps = tagsMapsFactory.createTagsMap("marriedSeperately");
		marriedSMap = tagsMaps.loadTagsForMap();
		
		tagsMaps = tagsMapsFactory.createTagsMap("headOfHousehold");
		headMap = tagsMaps.loadTagsForMap();
	}
	
	public static TagsMaps getInstance() {
		if(tagMapInstance == null)
			tagMapInstance = new TagsMaps();
		return tagMapInstance;
	}
	
	public HashMap<String,ArrayList<String>> getTxtMap(){
		return receiptTagsMapTxt;
	}
	
	public HashMap<String,ArrayList<String>> getXmlMap(){
		return receiptTagsMapXml;
	}
	
	public HashMap<String,ArrayList<String>> getSingleMap(){
		return singleMap;
	}
	
	public HashMap<String,ArrayList<String>> getMarriedJointlyMap(){
		return marriedJMap;
	}
	
	public HashMap<String,ArrayList<String>> getMarriedSeperatelyMap(){
		return marriedSMap;
	}
	
	public HashMap<String,ArrayList<String>> getHeadOfHouseholdMap(){
		return headMap;
	}
}
