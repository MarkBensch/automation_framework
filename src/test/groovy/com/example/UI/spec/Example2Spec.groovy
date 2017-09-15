package com.example.UI.spec

import com.example.UI.pages.ExamplePage
import com.example.UI.pages.ResultsPage
import com.example.util.TestCaseID
import com.gargoylesoftware.htmlunit.BrowserVersion
import geb.Browser
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import spock.lang.Title

@Title("this is a title or name of a Feature 2")
class Example2Spec extends BaseGebSpec{
	@TestCaseID("TC003")
	def "go to google search page 2"() {

		when: "the user navigates to the google search page"
			to ExamplePage

		then: "verify using at to make sure we are on the search page"
			at ExamplePage
	}

	@TestCaseID("TC004")
	def "do a google search 3"() {

		when: "navigate to search page"
			to ExamplePage

		and:"enter a search parameter"
			search("Chuck Norris")

		then: " check to see if the first item contains something we expect"

			waitFor {
				at ResultsPage
				result(0).text().contains("Chuck")
			}

	}
	@TestCaseID("TC005")
	def "this is a failing test"(){
		when: "the test needs to fail"
			to ExamplePage
		then: "we say 1==2"
			1==2
	}
	@TestCaseID("TC006")
	def "can i spin up a headless browser to change settings"(){
		when:
			def browser2 = new Browser(driver: new HtmlUnitDriver(BrowserVersion.CHROME, true))
		
			browser2.to ExamplePage
			
			browser2.page.search("speed racer")
			browser2.page.waitFor {
				browser2.at ResultsPage
				browser2.page.result(0).text().contains("Speed")
			}
			
			logger.info(browser2.driver)
		
		then:
			browser2.driver.title.contains("speed racer")
			browser2.quit()
	}

}
