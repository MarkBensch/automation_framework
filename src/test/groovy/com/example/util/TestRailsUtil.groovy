package com.example.util

import groovy.util.logging.Log4j2
import org.apache.http.HttpHeaders
import java.text.SimpleDateFormat
import java.util.Date

@Log4j2
class TestRailsUtil {

	def username = ConfigLoader.config().testRails.username
	def password = ConfigLoader.config().testRails.password
	def baseUrl = ConfigLoader.config().testRails.baseUrl
	def project_id = ConfigLoader.config().testRails.project_id
	def filterAutomation = ConfigLoader.config().testRails.automation_id
	RestUtil request = new RestUtil()
	def authKey = request.createBasicAuth(username,password)
	def dateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date())


	int makeTestRun() {
		return "testRun12345"
	}

	int makeTestRun(def testCases) {
		def add_run = "add_run/$project_id"
		def headers = ["${HttpHeaders.AUTHORIZATION}" : authKey]
		def body = ["name":"Automated test run $dateTime","include_all":false,"case_ids":testCases]
		def results = request.restPost(baseUrl + add_run,headers,request.buildJsonBody(body))
		return results.body.id
	}


	def getTestCases() {
		return [:]
	}

	def getTestCases(String filter) {
		def get_cases = "get_cases/$project_id"
		def headers = ["${HttpHeaders.AUTHORIZATION}" : authKey]
		def params = [ "type_id":filterAutomation]
		def results = request.executeWithJson(request.restGet(baseUrl + get_cases, headers, params ))
		
		return results.body.id
	}

	def updateRunResults(String runID, List<Map<String,Object>> testResults) {

	}

///https://github.com/codetojoy/talk_maritimedevcon_groovy/blob/master/exampleB_REST_Client/v2_groovy/RESTClient.groovy

}
