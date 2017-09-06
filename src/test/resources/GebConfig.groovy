import com.gargoylesoftware.htmlunit.BrowserVersion
import org.apache.commons.logging.LogFactory
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.safari.SafariDriver

import java.util.logging.Level


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
	HtmlUnit {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog")
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE)
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.SEVERE)


		caps = DesiredCapabilities.htmlUnit()
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)
//		caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true)
//		caps.setCapability(CapabilityType.BROWSER_VERSION,BrowserVersion.CHROME)

		driver = {
			new HtmlUnitDriver(caps)
		}
	}
}


