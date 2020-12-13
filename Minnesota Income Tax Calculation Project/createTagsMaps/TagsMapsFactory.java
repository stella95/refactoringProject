package createTagsMaps;

public class TagsMapsFactory {
	public ITagsMaps createTagsMap(String mapType) {
		switch(mapType) {
		case "txt": 
			return new TagsMapTxt();
		case "xml":
			return new TagsMapXml();

		default: return null;
		}

	}
}
