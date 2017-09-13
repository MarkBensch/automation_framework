package com.example.util


import groovy.transform.Memoized
import groovy.util.logging.Log4j2

@Log4j2
class ConfigLoader {
    static def slurper = new ConfigSlurper()
    static def confLocation = new File("src/test/resources/MainConfig.groovy")

    @Memoized
    static def config() {
        log.info("config loaded from: $confLocation")
        return slurper.parse(confLocation.toURL())
    }
}