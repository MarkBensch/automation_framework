package com.example.UI.spec


class ExampleSpec extends BaseGebSpec{

	def "go to google and pass the test"() {

		when:
			go "https://docs.gradle.org/current/release-notes.html"

		then:

			1==1
	}

	def "go to google and the test will fail"() {

		when:
			go "http://www.apple.com"
		then:

			1==2
	}

}
