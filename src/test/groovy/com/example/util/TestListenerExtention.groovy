package com.example.util

import groovy.util.logging.Log4j2
import org.spockframework.runtime.model.SpecInfo
import org.spockframework.runtime.extension.IGlobalExtension
@Log4j2
class TestListenerExtention implements IGlobalExtension {
    void start() {

    }

    void visitSpec(SpecInfo specInfo) {
        log.info("I can hear tests")
        specInfo.addListener(new TestCaseManagerListener())
    }

    void stop() {

    }

    /**
     * https://github.com/spockframework/spock/search?utf8=%E2%9C%93&q=IGlobalExtension&type=
     * https://stackoverflow.com/questions/20553688/spock-globalextension-is-not-loaded-grails
     * https://stackoverflow.com/questions/25967241/does-spock-have-test-event-listeners
     */
}