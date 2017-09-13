package com.example.UI.spec

import com.example.util.ConfigLoader
import com.example.util.GebHelperTrait
import geb.spock.GebSpec
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogType
import spock.lang.Shared

class BaseGebSpec extends GebSpec implements GebHelperTrait{
	@Shared
	Logger logger = LogManager.getLogger(getClass().name)


	def setup(){
		logger.info(ConfigLoader.config().ui.baseUrl)
	}

	def cleanup() {

		try {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER)
			if (logEntries.size()>0) {
				logger.info("Browser errors:")

				logEntries.each { logEntry ->
					logger.info("[${logEntry.level}] ${logEntry.message}")
				}
			}
			else { logger.info("No Browser errors encountered")}
		} catch(Exception e){
			logger.warn("Browser does not support BrowserLogs")
		}
	}

}



