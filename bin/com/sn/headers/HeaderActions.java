package com.framework.headers;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.framework.report.ExtentReportManager;

public class HeaderActions {

	Logger logger = LogManager.getLogger(HeaderActions.class);

	public HashMap<String, String> getAllHeaders(Properties prop, String keyPrefix) {

		Set<Object> allKeys = prop.keySet();
		logger.info("header keys object is created.");



		HashMap<String, String> map = null;

		if (allKeys.size() == 0) {

			logger.error("Properties file is empty.");

			ExtentReportManager.failTest("Properties file is empty.");

		} else {

			map = new HashMap<>();

			for (Object keyObject : allKeys) {

				String key = (String) keyObject;

				if (key.startsWith(keyPrefix)) {

					map.put(key.replace(keyPrefix, ""), prop.getProperty(key));
				}

			}

			if (map.isEmpty()) {

				ExtentReportManager.failTest("No Headers found starting with " + keyPrefix);
			}

		}

		return map;

	}

}
