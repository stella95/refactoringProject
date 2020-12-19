package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import inputManagePackage.InputSystem;
import outputManagePackage.SaveInputFile;

public class SaveInputFileTest {
	
	private SaveInputFile saveInputFile;
	private InputSystem inputSystem;
	private ArrayList<String> taxpayersInfoFiles;
	private String inputFilename;
	private File standarFile;;
	private File savedFile;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		inputSystem =InputSystem.getInstance();
		saveInputFile = SaveInputFile.getInstance();
		taxpayersInfoFiles = new ArrayList<String>();
		taxpayersInfoFiles.add("testinput.txt");
		inputFilename="C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testInputFile\\testinput.txt";
		standarFile = new File("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testOutputFile\\standarFile.txt");
	}

	@Test
	public void testtestSaveInputFile() throws IOException {
		inputSystem.addTaxpayersDataFromFilesIntoDatabase("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testInputFile", taxpayersInfoFiles);
		saveInputFile.saveUpdatedTaxpayerInputFile(inputFilename, 0, "txt");
		savedFile = new File(inputFilename);
		assertTrue("The files differ(saveInputFile))!", FileUtils.contentEquals(standarFile, savedFile));
	}

}
