import com.gargoylesoftware.htmlunit.BrowserVersion
import org.apache.commons.logging.LogFactory
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.safari.SafariDriver

import java.util.logging.Level

import static org.openqa.selenium.remote.CapabilityType.*

LoggingPreferences logPrefs = new LoggingPreferences()
logPrefs.enable(LogType.BROWSER, Level.ALL)
DesiredCapabilities caps

environments{
	Firefox {
		caps = DesiredCapabilities.firefox()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		driver = { new FirefoxDriver(caps) }
	}
	Safari {
		caps = DesiredCapabilities.safari()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		driver = { new SafariDriver(caps) }
	}
	Chrome {
		caps = DesiredCapabilities()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		driver = { new ChromeDriver(caps) }
	}
	SauceLab {
		//TODO: come up with a parser for geb.remote list of caps and setting
		// geb.remote = <<browser>>:<<version>>:<<platform>>
		def remoteCapabilities = System.getProperty("geb.remoteServer").split(/(:|;|,)/)
		caps = DesiredCapabilities()
		caps.setCapability(BROWSER_NAME, remoteCapabilities[0])
		caps.setCapability(VERSION, remoteCapabilities[1])
		caps.setCapability(PLATFORM, remoteCapabilities[2])
		def username = ""
		def accessKey = ""
		def saucelabURL = "http://$username:$accessKey@ondemand.saucelabs.com:80/wd/hub"
		driver = {
			def remoteWebDriverServerUrl = new URL(saucelabURL)
			new RemoteWebDriver(remoteWebDriverServerUrl, caps)
		}
	}
	BrowserStack {
		//TODO: come up with a parser for geb.remoteServer list of caps and setting
		// geb.remoteServer = <<browser>>:<<version>>:<<platform>>
		def remoteCapabilities = System.getProperty("geb.remoteServer").split(/(:|;|,)/)
		caps = DesiredCapabilities.chrome()
		caps.setCapability(BROWSER_NAME, remoteCapabilities[0])
		caps.setCapability(VERSION, remoteCapabilities[1])
		caps.setCapability(PLATFORM, remoteCapabilities[2])
		def username = ""
		def accessKey = ""
		def saucelabURL = "http://$username:$accessKey@hub-cloud.browserstack.com/wd/hub"
		driver = {
			def remoteWebDriverServerUrl = new URL(saucelabURL)
			new RemoteWebDriver(remoteWebDriverServerUrl, caps)
		}
	}
	Grid {

	}
	HtmlUnit {
		//This Section makes Unit Driver not so loud unless it needs to be
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog")
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE)
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.SEVERE)
		//////

		caps = DesiredCapabilities.htmlUnit()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true)
		caps.setCapability(CapabilityType.BROWSER_VERSION, BrowserVersion.CHROME)

		driver = { new HtmlUnitDriver(caps) }
	}
}


