package com.example.UI.spec

import geb.spock.GebSpec
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogType
import spock.lang.Shared

class BaseGebSpec extends GebSpec{
	@Shared
	Logger logger

	def setup(){
		logger = LogManager.getLogger(getClass().name)
		logger.info(System.getProperty("geb.env"))
		logger.info(driver)
		logger.info(System.getProperty("geb.remote"))
	}

	def cleanup() {

		try {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER)
			if (logEntries) {
				logger.info("Browser errors:")

				logEntries.each { logEntry ->
					logger.info("[${logEntry.level}] ${logEntry.message}")
				}
			}
		} catch(Exception e){
			logger.warn("did not generate any console logs")
		}
	}

}



