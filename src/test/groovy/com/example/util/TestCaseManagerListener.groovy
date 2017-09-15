package com.example.util

import groovy.util.logging.Log4j2
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo
import org.spockframework.runtime.model.SpecInfo

@Log4j2
class TestCaseManagerListener extends AbstractRunListener {
    boolean testFailed
	def testCase = [:]
	def testCases = [[:]]

	void beforeSpec(SpecInfo spec) {
		log.info("starting Spec: $spec.name")
		if (System.properties."example.testrunID") {
			log.info("set test run ID: ${System.properties."example.testrunID"}")
		} else {
			log.info("create test run ID: ")
			System.properties."example.testrunID" = "testRun12345"
		}

		super.beforeSpec(spec)
	}

	void beforeFeature(FeatureInfo feature) {
        testFailed = false
		testCase = [:]
        log.info("starting: $feature.name")
		super.beforeFeature(feature)
     }

    void error(ErrorInfo error) {
        testFailed = true
		log.warn(error.exception)
		testCase.'error' = error.exception
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
			testCase.ID = testCaseID
			testCase.status = testFailed ? "Failed" : "Passed"


		}
		catch(java.lang.NullPointerException e){
			log.info("test Case #: No testcase ID")
			testCase = [:]
		}
		testCases.add(testCase)
		super.afterFeature(feature)
    }
    void afterSpec(SpecInfo spec) {
		log.warn("tests sending to API: $testCases")
		super.afterSpec(spec)
    }

}
