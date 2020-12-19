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
import outputManagePackage.SaveLogFile;

public class SaveLogFileTest {
	
	private SaveLogFile saveLogFile;
	private InputSystem inputSystem;
	private ArrayList<String> taxpayersInfoFiles;
	private String folderPath;
	private File standarFile;;
	private File savedFile;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		inputSystem =InputSystem.getInstance();
		saveLogFile = SaveLogFile.getInstance();
		taxpayersInfoFiles = new ArrayList<String>();
		taxpayersInfoFiles.add("testinput.txt");
		folderPath="C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testOutputFile";
		standarFile = new File("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testOutputFile\\standarLogFile.txt");
	}

	@Test
	public void testtestSaveLogFile() throws IOException {
		inputSystem.addTaxpayersDataFromFilesIntoDatabase("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testInputFile", taxpayersInfoFiles);
		saveLogFile.saveTaxpayerInfoToLogFile(folderPath, 0, "txt");
		savedFile = new File("C:\\Users\\stell\\eclipse-workspace\\Minnesota Income Tax Calculation Project\\files\\testOutputFile\\130456093_LOG.txt");
		assertTrue("The files differ(saveLogFile))!", FileUtils.contentEquals(standarFile, savedFile));
	}

}
