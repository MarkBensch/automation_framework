import com.gargoylesoftware.htmlunit.BrowserVersion
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.apache.commons.logging.LogFactory
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.ie.InternetExplorerDriver
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
		caps = DesiredCapabilities.chrome()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		driver = { new ChromeDriver(caps) }
	}
	IE {
		caps = DesiredCapabilities.internetExplorer()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true)
		caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true)
		driver = { new InternetExplorerDriver(caps) }
	}
	Edge {
		caps = DesiredCapabilities.edge()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
		driver = { new EdgeDriver(caps) }
	}
	SauceLab {
		//TODO: come up with a parser for geb.remote list of caps and setting
		// geb.remote = <<browser>>:<<version>>:<<platform>>
		def remoteCapabilities = System.getProperty("geb.remoteServer").split(/(:|;|,)/)
		caps = new DesiredCapabilities()
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
		caps = new DesiredCapabilities()
		caps.setCapability(BROWSER_NAME, remoteCapabilities[0])
		caps.setCapability(VERSION, remoteCapabilities[1])
		caps.setCapability(PLATFORM, remoteCapabilities[2])
		def username = ""
		def accessKey = ""
		def browserstackURL = "http://$username:$accessKey@hub-cloud.browserstack.com/wd/hub"
		driver = {
			def remoteWebDriverServerUrl = new URL(browserstackURL)
			new RemoteWebDriver(remoteWebDriverServerUrl, caps)
		}
	}
	Grid {

	}
	MobileSauceLab {
		// <<platformName>>:<<platformVersion>>:<<browserName>>:<<deviceOrientation>>:<<deviceName>>:<<appiumVersion>>
		def remoteCapabilities = System.getProperty("geb.remoteMobile").split(/(:|;|,)/)
		caps = new DesiredCapabilities()
		caps.setCapability("platformName", remoteCapabilities[0])
		caps.setCapability("platformVersion", remoteCapabilities[1])
		caps.setCapability("browserName", remoteCapabilities[2])
		caps.setCapability("deviceOrientation", remoteCapabilities[3])
		caps.setCapability("deviceName", remoteCapabilities[4])
		caps.setCapability("appiumVersion", remoteCapabilities[5])
		def username = ""
		def accessKey = ""
		def saucelabURL = "http://$username:$accessKey@ondemand.saucelabs.com:80/wd/hub"
		def remoteWebDriverServerUrl = new URL(saucelabURL)
		if (remoteCapabilities[0] == "Android") {
			driver = { new AndroidDriver<>(remoteWebDriverServerUrl, caps) }
		} else {
			driver = { new IOSDriver<>(remoteWebDriverServerUrl, caps) }
		}
	}
	MobileBrowserStack {
		// <<os>>:<<os_version>>:<<browser>>:<<deviceOrientation>>:<<deviceName>>:<<appiumVersion>>:<<realMobile>>
		def remoteCapabilities = System.getProperty("geb.remoteMobile").split(/(:|;|,)/)
		caps = new DesiredCapabilities()
		caps.setCapability("os", remoteCapabilities[0])
		caps.setCapability("os_version", remoteCapabilities[1])
		caps.setCapability("browser", remoteCapabilities[2])
		caps.setCapability("deviceOrientation", remoteCapabilities[3])
		caps.setCapability("device", remoteCapabilities[4])
		caps.setCapability("browserstack.appium_version", remoteCapabilities[5])
		caps.setCapability("realMobile", remoteCapabilities[6])
		def username = ""
		def accessKey = ""
		def browserstackURL = "http://$username:$accessKey@hub-cloud.browserstack.com/wd/hub"
		def remoteWebDriverServerUrl = new URL(browserstackURL)
		if (remoteCapabilities[0] == "android") {
			driver = { new AndroidDriver<>(remoteWebDriverServerUrl, caps) }
		} else {
			driver = { new IOSDriver<>(remoteWebDriverServerUrl, caps) }
		}
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




