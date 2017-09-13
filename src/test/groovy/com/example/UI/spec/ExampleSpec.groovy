package com.example.UI.spec

import com.example.UI.pages.ExamplePage
import com.example.UI.pages.ResultsPage


class ExampleSpec extends BaseGebSpec{

	def "go to google search page"() {

		when: "the user navigates to the google search page"
			to ExamplePage

		then: "verify using at to make sure we are on the search page"
			at ExamplePage
	}

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

}
