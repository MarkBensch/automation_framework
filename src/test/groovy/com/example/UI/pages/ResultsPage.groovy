package com.example.UI.pages

class ResultsPage extends BasePage {
    static atCheckWaiting = true
    static at = { title.contains("- Google Search") }
    static content = {
        results { $("#rso > div:nth-child(1) > div > div") }
        result { index -> results[index] }
    }
}
