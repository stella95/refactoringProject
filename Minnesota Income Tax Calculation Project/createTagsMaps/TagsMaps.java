package createTagsMaps;

import java.util.ArrayList;
import java.util.HashMap;

public class TagsMaps {
	
	private static TagsMaps tagMapInstance = null;
	private HashMap<String,ArrayList<String>> receiptTagsMapTxt = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> receiptTagsMapXml = new HashMap<String,ArrayList<String>>();
	private ITagsMaps tagsMaps;
	private TagsMapsFactory tagsMapsFactory = new TagsMapsFactory();
	
	public TagsMaps(){
		tagsMaps = tagsMapsFactory.createTagsMap("txt");
		receiptTagsMapTxt = tagsMaps.loadTagsForMap();
		tagsMaps = tagsMapsFactory.createTagsMap("xml");
		receiptTagsMapXml = tagsMaps.loadTagsForMap();
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
}
