package com.truvideo.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.truvideo.utility.JavaUtility;
import io.appium.java_client.AppiumDriver;

public class TestUtils extends JavaUtility {
static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir") + "/Reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("RC Truvideo Web App");
		reporter.config().setDocumentTitle("Web Automation Test Report");
		reporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("5 Exceptions", "RC Truvideo");
		extent.setSystemInfo("Browser", "chrome");
		return extent;
	}

	public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationField = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName
				+ ".png";
		FileUtils.copyFile(source, new File(destinationField));
		return destinationField;
	}
	
	public String getScreenShotPath(String testCaseName, Page page) throws IOException {
	    String destinationField = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName + ".png";
	    // Capture the screenshot and save it to the destination path
	    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(destinationField)));
	    return destinationField;
	}
	
	//Testing email screenshot from RK
	public String getBase64Screenshot(Page page) {
	    String base64Screenshot = "";
	    try {
	        // Playwright's method to take a screenshot in Base64 format
	        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setType(ScreenshotType.PNG));
	        base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return base64Screenshot;
	}


	public void sendReportToEmail() {
		//try {
			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(System.getProperty("user.dir") + "/Reports/index.html");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Extent Report");
			
			try {
		        String reportPath;

		        // Determine the path based on the environment
		        if (System.getProperty("os.name").toLowerCase().contains("win")) {
		            reportPath = "D:/TruvideoWeb_Playwright/Reports/Index.html"; // Adjust for Windows
		        } else {
		            reportPath = System.getProperty("user.dir") + "/Reports/Index.html"; // Default relative path
		        }

		        File reportFile = new File(reportPath);
		        if (!reportFile.exists()) {
		            System.err.println("Report file not found at: " + reportPath);
		            return;
		        }

		        System.out.println("Report file found: " + reportPath);
			
			
			// Create the email message
//			MultiPartEmail email = new MultiPartEmail();
//			email.setHostName("smtp.mail.yahoo.com");
//			email.setSmtpPort(465); // port
//			email.setTLS(true);
//			email.setAuthenticator(new DefaultAuthenticator("sandip.chopkar@5exceptions.com", "scusooxsoohzvlfz")); // pass=AppPass
//			email.setSSLOnConnect(true); // Use SSL
//			email.setFrom("sandip.chopkar@5exceptions.com");
//			email.addTo("rahul.kapse@5exceptions.com");
//			//email.addTo("sandipchopkar789@gmail.com");
//			email.addTo("rk434kapse@gmail.com");
//			email.setSubject("Web App Automation Report");
//			email.setMsg("Please find the attached Automation Report For Truvideo Web App.");
//			
			
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.mail.yahoo.com");
			email.setSmtpPort(465); // port
			email.setTLS(true);
			//scusooxsoohzvlfz
			email.setAuthenticator(new DefaultAuthenticator("rahul.kapse@5exceptions.com", "lnvpdqluxdqhdufz")); // pass=AppPass
			email.setSSLOnConnect(true); // Use SSL
			email.setFrom("rahul.kapse@5exceptions.com");
			//email.setFrom("sandip.chopkar@5exceptions.com");
			
			//email.addTo("rahul.kapse@5exceptions.com");
			email.addTo("gopal.chandre@5exceptions.com");
			email.addTo("yash.modi@5exceptions.com");
			email.addTo("suraj.kushwah@5exceptions.com");
			email.setSubject("Automation Report");
			email.setMsg("Please find the attached Automation Report For Truvideo Flutter App.");
			
			// Attach the file
			email.attach(attachment);
			// Send the email
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getTicketID(String testCaseName) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/TestCaseData/ManualTestCases.xlsx";
		String searchValue = testCaseName;
		List<String> tags = new ArrayList<>();
		String testCaseDescription = ""; // To store data from the third column
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0); // Reading the first sheet
			// Iterate through each row
			for (Row row : sheet) {
				Cell firstCell = row.getCell(0); // First column
				Cell secondCell = row.getCell(1); // Second column
				Cell thirdCell = row.getCell(2); // Third column
				if (firstCell != null && firstCell.getCellType() == CellType.STRING) {
					String cellValue = firstCell.getStringCellValue();
					if (cellValue.contains(searchValue)) {
						if (secondCell != null && secondCell.getCellType() == CellType.STRING) {
							String[] tagArray = secondCell.getStringCellValue().split(",");
							tags.addAll(Arrays.asList(tagArray));
						}
						if (thirdCell != null && thirdCell.getCellType() == CellType.STRING) {
							testCaseDescription = thirdCell.getStringCellValue();
							tags.add(testCaseDescription);
						}
					}
				}
			}
			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tags;
	}
	 public static Object[][] readCSV(String filePath) throws CsvException {
	        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
	            String[] values;
	            csvReader.readNext(); // Skip header
	            return csvReader.readAll().stream()
	                    .map(row -> new Object[]{row[0], row[1], row[2], row[3]})
	                    .toArray(Object[][]::new);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return new Object[0][0];
	        }
	 
	 }
}
