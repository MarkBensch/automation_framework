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
	

    void beforeFeature(FeatureInfo feature) {
        testFailed = false
        log.info("starting: $feature.name")
     }

    void error(ErrorInfo error) {
        testFailed = true
		log.warn(error.exception)
    }

    void afterIteration(IterationInfo iteration) {
        //log.info("test Name: $iteration.name and Test: ${testFailed? "Failed" : "Passed"}")
        //log.info("test Case #: " + IterationInfo().featureMethod.getReflection().annotation)
        //log.info("iteration: " + iteration.name)
    }
    void afterFeature(FeatureInfo feature) {
        log.info("test Name: \"$feature.name\" and Test: ${testFailed? "Failed" : "Passed"}")
		try {
			def testCase = feature.featureMethod.getReflection().getAnnotation(com.example.util.TestCaseID.class).value()
			log.info("test Case #: $testCase")
		}
		catch(java.lang.NullPointerException e){
			log.info("test Case #: No testcase ID")
		}
    }
    void afterSpec(SpecInfo spec) {

    }

}
