package com.framework.pojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.report.ExtentReportManager;

public class PojoActions {

	Logger logger = LogManager.getLogger(PojoActions.class);

	@SuppressWarnings("unchecked")
	public Object createPOJOFromJsonFile(File jsonFile, Class cl) {

		ObjectMapper mapper = new ObjectMapper();

		String fileExtension = FilenameUtils.getExtension(jsonFile.getName());

		logger.info(jsonFile.getName() + " extension is :" + fileExtension);

		if (fileExtension.equalsIgnoreCase("json") && jsonFile.length() > 0 && jsonFile.isFile()) {
			try {
				return mapper.readValue(jsonFile, cl);
			} catch (IOException e) {
				logger.error("Exception occured in createPOJOFromJsonFile.");

				ExtentReportManager.failTest("Exception occured in createPOJOFromJsonFile " + e.getMessage());
			}
		}

		else {
			logger.error("Json file is not valid.");

			ExtentReportManager.failTest("Json file is not valid. hmm");

		}
		return null;

	}

	public File createJsonFileFromPOJO(String filePath, String fileName, Object object) {

		ObjectMapper mapper = new ObjectMapper();

		File outPutFile = new File(System.getProperty(filePath + fileName));

		try {
			outPutFile.createNewFile();
		} catch (IOException e) {
			logger.error("Error occured in creating file." + e.getLocalizedMessage());
			ExtentReportManager.failTest("Error occured in creating file.");

		}

		try {
			mapper.writeValue(outPutFile, object);
		} catch (IOException e) {
			logger.error("Error occured in createJsonFileFromPOJO." + e.getLocalizedMessage());

			ExtentReportManager.failTest("Error occured in createJsonFileFromPOJO.");

		}

		return outPutFile;

	}

	public String createJSONFileFromString(String jsonStr, String filePath, String fileName) {

		File file = new File(filePath + fileName);

		try {
			file.createNewFile();
		} catch (IOException e) {
			logger.error("Error occured in creating file." + e.getLocalizedMessage());
			ExtentReportManager.failTest("Error occured in creating file.");

		}

		FileWriter writter;
		try {
			writter = new FileWriter(file);
			writter.write(jsonStr);

			writter.close();
		} catch (IOException e) {
			logger.error("Error occured in createJSONFileFromString." + e.getLocalizedMessage());
			ExtentReportManager.failTest("Error occured in createJSONFileFromString.");

		}

		return file.getAbsolutePath();

	}

}
