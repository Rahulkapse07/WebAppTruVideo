package com.truvideo.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.FilePayload;

public class JavaUtility {
	
 
	
	public static Properties prop;
	public Logger logger = LogManager.getLogger(this.getClass().getName());
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();
	
	
//	 // Start Trace Recording
//    public static void startTracing(String testName) {
//        testName.tracing().start(new Tracing.StartOptions()
//            .setScreenshots(true)
//            .setSnapshots(true)
//            .setSources(true));
//    }
	
	

    // Stop Trace Recording and Export
    public static void stopTracing(BrowserContext context, String testCaseName) {
        String tracePath = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName + "-trace.zip";
        context.tracing().stop(new Tracing.StopOptions()
            .setPath(Paths.get(tracePath)));
    }
	
	

	public String getRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));

		}
		return sb.toString();
	}

	public int getRandomNumber(int length) {
		Random random = new Random();
		int min = (int) Math.pow(10, length - 1); // Minimum value with specified length
		int max = (int) Math.pow(10, length) - 1; // Maximum value with specified length
		return random.nextInt(max - min + 1) + min;
	}

	// this method is used to initialize the properties from config file
//	public Properties init_prop() {
//		try {
//			prop = new Properties();
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException f) {
//			f.printStackTrace();
//		}
//		return prop;
//	}

//	     public Properties init_prop() {
//	    	
//	    	        try {
//	    	            prop = new Properties();
//
//	    	            // Get the path from the system property or default to the relative path
//	    	            String filePath = System.getProperty("config.path");
//
//	    	            if (filePath == null || filePath.isEmpty()) {
//	    	                // Default relative path if no system property is set
//	    	                filePath = "./src/test/resources/config/config.properties";
//	    	            }
//
//	    	            // Log the file path being used (optional)
//	    	            System.out.println("Loading configuration from: " + filePath);
//
//	    	            // Load the properties file
//	    	            FileInputStream ip = new FileInputStream(filePath);
//	    	            prop.load(ip);
//
//	    	        } catch (FileNotFoundException e) {
//	    	            e.printStackTrace();
//	    	            throw new RuntimeException("Configuration file not found: " + e.getMessage());
//	    	        } catch (IOException f) {
//	    	            f.printStackTrace();
//	    	            throw new RuntimeException("Failed to load configuration file: " + f.getMessage());
//	    	        }
//	    	        return prop;
//	    	    }
// 	public Properties init_prop() {
//	    try {
//	        prop = new Properties();
//	        String filePath = System.getProperty("config.path", "./src/test/resources/config/config.properties");
//
//	        // Log the file path for debugging
//	       // System.out.println("Loading config file from: " + filePath);
//
//	        // Load the properties file
//	        FileInputStream ip = new FileInputStream(filePath);
//	        prop.load(ip);
//
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	        throw new RuntimeException("Configuration file not found: " + e.getMessage());
//	    } catch (IOException f) {
//	        f.printStackTrace();
//	        throw new RuntimeException("Failed to load configuration file: " + f.getMessage());
//	    }
//	    return prop;
//	}

	

	public static String takeScreenshot(Page page) {
		String path = System.getProperty("user.dir" + "/screenshots/" + System.currentTimeMillis() + ".png");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}

	public boolean isDateInCurrentWeek(String dateFromList) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
		LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
		LocalDate now = LocalDate.now();
		LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endOfWeek = startOfWeek.plusDays(6);
		return (givenDate.isEqual(startOfWeek) || givenDate.isAfter(startOfWeek))
				&& (givenDate.isEqual(endOfWeek) || givenDate.isBefore(endOfWeek));
	}

	public boolean isDateInCurrentMonth(String dateFromList) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
		LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
		LocalDate now = LocalDate.now();
		return givenDate.getYear() == now.getYear() && givenDate.getMonth() == now.getMonth();
	}

	public static boolean isDateInRange(String dateFromList, String fromDateStr, String toDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
		LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
		LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
		LocalDate toDate = LocalDate.parse(toDateStr, formatter);
		return (givenDate.isEqual(fromDate) || givenDate.isAfter(fromDate))
				&& (givenDate.isEqual(toDate) || givenDate.isBefore(toDate));
	}

	public void createAndUploadCsvFile_Advisor(Page page) {
		String filePath = "./src/test/resources/CreateUserData" + "unique_data_advisor.csv";
		createCsvFile_Advisor(filePath);
		try {
			byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
			page.setInputFiles("input[type='file']", new FilePayload(filePath, "text/csv", fileContent));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createAndUploadCsvFile_Technician(Page page) {
		String filePath = "./src/test/resources/CreateUserData" + "unique_data_technician.csv";
		createCsvFile_Technician(filePath);
		try {
			byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
			page.setInputFiles("input[type='file']", new FilePayload(filePath, "text/csv", fileContent));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	private String firstUser="";
	private void createCsvFile_Advisor(String filePath) {
		ArrayList<String>emailList=new ArrayList<String>();
		String[] headers = { "Firstname", "Lastname", "Title", "Phone number", "Email" }; //, "Username"
		try (FileWriter writer = new FileWriter(filePath)) {
			for (String header : headers) {
				writer.append(header).append(",");
			}
			writer.append("\n");
			for (int i = 0; i < 3; i++) {
				String email=generateUniqueEmail();
				emailList.add(email);
				String[] data = { generateUniqueString(), generateUniqueString(), "Advisor", "1234567890",
						email };  //, generateUniqueString()
				for (String field : data) {
					writer.append(field).append(",");
				}
				writer.append("\n");
				//logger.info(emailList.get(0).toString());
				
			}
			 setFirstUser(emailList.get(0).toString());
			//logger.info(firstUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createCsvFile_Technician(String filePath) {
		String[] headers = { "Firstname", "Lastname", "Title" };
		try (FileWriter writer = new FileWriter(filePath)) {
			for (String header : headers) {
				writer.append(header).append(",");
			}
			writer.append("\n");
			for (int i = 0; i < 3; i++) {
				String[] data = { generateUniqueString(), generateUniqueString(), "Technician" };
				for (String field : data) {
					writer.append(field).append(",");
				}
				writer.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String generateUniqueEmail() {
		return "user" + getRandomString(5) + "@example.com";
	}

	protected String generateUniqueString() {
		return "Text" + getRandomString(5);
	}

	// ---------------------------Read Csv file-------------
	

	public String getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	public boolean waitForAndLogVisibility(Locator locator, String elementName) {
	    try {
	        locator.waitFor(); 
	        logger.info(elementName + " is visible.");
	        return true;
	    } catch (org.openqa.selenium.TimeoutException e) {
	        logger.error(elementName + " is not visible.");
	        return false;
	    }
	}


}
