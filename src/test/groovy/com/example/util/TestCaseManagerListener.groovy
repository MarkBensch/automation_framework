package com.example.util

import groovy.util.logging.Log4j2
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo
import org.spockframework.runtime.model.SpecInfo


@Log4j2
class TestCaseManagerListener extends AbstractRunListener {
	def testRails = new TestRailsUtil()
    boolean testFailed
	def result = [:]
	def testResults = [[:]]

	void beforeSpec(SpecInfo spec) {
		log.info("starting Spec: $spec.name")
		if (System.properties."example.testrunID") {
			log.info("set test run ID: ${System.properties."example.testrunID"}")
		} else {
			log.info("get testcases")
		//	def tests = testRails.getTestCases("automation")
			log.info("create test run ID: ")
			System.properties."example.testrunID" = "ID" //testRails.makeTestRun(tests)
		}

		super.beforeSpec(spec)
	}

	void beforeFeature(FeatureInfo feature) {
        testFailed = false
		result = [:]
        log.info("starting: $feature.name")
		super.beforeFeature(feature)
     }

    void error(ErrorInfo error) {
        testFailed = true
		log.warn(error.exception)
		result.'error' = error.exception
		super.error(error)
    }

    void afterIteration(IterationInfo iteration) {
        //log.info("test Name: $iteration.name and Test: ${testFailed? "Failed" : "Passed"}")
        //log.info("test Case #: " + IterationInfo().featureMethod.getReflection().annotation)
        //log.info("iteration: " + iteration.name)
    }
    void afterFeature(FeatureInfo feature) {


		log.info("test Name: \"$feature.name\" and Test: ${testFailed ? "Failed" : "Passed"}")
		try {
			def testCaseID = feature.featureMethod.getReflection().getAnnotation(com.example.util.TestCaseID.class).value()
			log.info("test Case #: $testCaseID")
			result.ID = testCaseID
			result.status = testFailed ? "Failed" : "Passed"


		}
		catch(java.lang.NullPointerException e){
			log.info("test Case #: No testcase ID")
			result = [:]
		}
		testResults.add(result)
		super.afterFeature(feature)
    }
    void afterSpec(SpecInfo spec) {
		log.warn("tests sending to API: $testResults")
		testRails.updateRunResults(System.properties."example.testrunID", testResults)
		super.afterSpec(spec)
    }

}
