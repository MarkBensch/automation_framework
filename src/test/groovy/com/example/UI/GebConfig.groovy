package com.example.UI

//import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.safari.SafariDriver

import java.util.logging.Level


//LoggingPreferences logPrefs = new LoggingPreferences()
//logPrefs.enable(LogType.BROWSER, Level.ALL)
//DesiredCapabilities caps
driver = { new SafariDriver() }
//environments{
//	Firefox {
//		caps = DesiredCapabilities.firefox()
//		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
//		driver = { new FirefoxDriver(caps) }
//	}
//	Safari {
//		caps = DesiredCapabilities.safari()
//		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
//		driver = { new SafariDriver(caps) }
//	}
//}


