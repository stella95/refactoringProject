package createTagsMaps;

public class TagsMapsFactory {
	public ITagsMaps createTagsMap(String mapType) {
		switch(mapType) {
		case "txt": 
			return new TagsMapTxt();
		case "xml":
			return new TagsMapXml();
		case "single":
			return new MapSingle();
		case "marriedJointly":
			return new MapSingle();
		case "marriedSeperately":
			return new MapSingle();
		case "headOfHousehold":
			return new MapSingle();

		default: return null;
		}

	}
}
