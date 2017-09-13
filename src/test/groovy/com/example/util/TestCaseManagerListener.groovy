package com.example.util

import groovy.util.logging.Log4j2
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.ErrorInfo

@Log4j2
class TestCaseManagerListener extends AbstractRunListener {
    void error(ErrorInfo error) {
        log.warn( "Test failed: ${error.method.name}")
        // Do other handling here
    }

}
