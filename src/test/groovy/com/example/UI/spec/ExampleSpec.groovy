package com.example.UI.spec

import com.example.UI.pages.ExamplePage
import com.example.UI.pages.ResultsPage
import com.example.util.TestCaseID
import spock.lang.Title

@Title("this is a title or name of a Feature")
class ExampleSpec extends BaseGebSpec{
	@TestCaseID("TC002")
	def "go to google search page"() {

		when: "the user navigates to the google search page"
			to ExamplePage

		then: "verify using at to make sure we are on the search page"
			at ExamplePage
	}

	@TestCaseID("TC001")
	def "do a google search"() {

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
	def "this is a failing test"(){
		when: "the test needs to fail"
			to ExamplePage
		then: "we say 1==2"
			1==2
	}

}
