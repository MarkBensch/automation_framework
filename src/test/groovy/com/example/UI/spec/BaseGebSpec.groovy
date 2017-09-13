package com.example.UI.spec

import com.example.util.ConfigLoader
import geb.spock.GebSpec
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogType
import spock.lang.Shared

class BaseGebSpec extends GebSpec{
	@Shared
	Logger logger = LogManager.getLogger(getClass().name)

	@Shared
	def config = new ConfigLoader().config()


	def setup(){
		logger.info("hellllllo world")
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



