package com.example.util


import groovy.transform.Memoized
import groovy.util.logging.Log4j2

@Log4j2
class ConfigLoader {
    @Memoized
    def config() {
        log.info("loading config")
        new ConfigSlurper().parse(new File("src/test/resources/MainConfig.groovy").toURL())

    }
}