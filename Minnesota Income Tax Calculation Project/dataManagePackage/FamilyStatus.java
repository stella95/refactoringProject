package dataManagePackage;

import java.util.ArrayList;
import java.util.HashMap;

import createTagsMaps.TagsMaps;

public class FamilyStatus {
	
	private String familyStatus;
	private static final String SINGLE = "single";
	private static final String MARRIED_FILLING_JOINTLY = "married filing jointly";
	private static final String MARRIED_FILLING_SEPARATELY = "married filing separately";
	private static final String HEAD_OF_HOUSEHOLD = "head of household";
	private TagsMaps tagsMap;

	
	public FamilyStatus(String familyStatus) {
		tagsMap = TagsMaps.getInstance();
		this.familyStatus=familyStatus;
	}
	
	public HashMap<String,ArrayList<String>> setMapForFamilyStatus(){
		switch(familyStatus.toLowerCase()){
		case(SINGLE):
			return tagsMap.getSingleMap();
		case(MARRIED_FILLING_JOINTLY):
			return tagsMap.getMarriedJointlyMap();
		case(MARRIED_FILLING_SEPARATELY):
			return tagsMap.getMarriedSeperatelyMap();
		case(HEAD_OF_HOUSEHOLD):
			return tagsMap.getHeadOfHouseholdMap();
		default: return null;
		}
	}
	
	public String getFamilyStatus() {
		return familyStatus;
	}
}
