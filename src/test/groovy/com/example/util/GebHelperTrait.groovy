package com.example.util

import geb.Browser
import geb.navigator.Navigator

trait GebHelperTrait {
    // Wait for the element to be displayed and click
    Navigator waitClick(Navigator navigator) {
        waitFor { navigator.displayed }
        navigator.click()
        navigator
    }

    Navigator clearText(Navigator navigator) {
        waitFor { navigator.displayed }
        navigator.firstElement().clear()
        navigator
    }

    // Get the last URL segment without params (after the final `/`, before `?`)
    String getLastURLSegment(Browser browser) {
        def url = browser.getCurrentUrl().substring(browser.getCurrentUrl().lastIndexOf('/') + 1)

        if (url.contains('?')) {
            url = url.substring(0, url.indexOf('?'))
        }

        url
    }

    def removeOnUnloadEvents(Browser browser) {
        browser.js.exec("window.onbeforeunload = function(e){};")
    }

    def getBrowserType() {
        PropertyManager.getGebEnv()
    }
}