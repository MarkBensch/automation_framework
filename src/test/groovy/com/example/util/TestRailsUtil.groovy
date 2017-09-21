package com.example.util

import groovy.util.logging.Log4j2

@Log4j2
class TestRailsUtil {

	def username
	def passworda
	def baseUrl
	def project_id


	String makeTestRun() {
		return "testRun12345"
	}

	String makeTestRun(List testCases) {
		return "testRun12345"
	}


	List getTestCases() {
		return [:]
	}

	List getTestCases(def filter) {
		return [:]
	}

	def updateRunResults(String runID, List<Map> testResults) {

	}

///https://github.com/codetojoy/talk_maritimedevcon_groovy/blob/master/exampleB_REST_Client/v2_groovy/RESTClient.groovy

}
