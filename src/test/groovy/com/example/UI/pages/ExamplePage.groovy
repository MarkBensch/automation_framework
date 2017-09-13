package com.example.UI.pages

import org.openqa.selenium.Keys


class ExamplePage extends BasePage {
    static url = "search"
    static at = { title == "Google" }
    static content = {
        searchField { $("input[name=q]") }
        searchButton(to: ResultsPage) { $("#sbtc > div.gstl_0.sbdd_a > div:nth-child(2) > div.sbdd_b > div > ul > li:nth-child(5) > div > span:nth-child(1) > span > input") }
    }

    void search(String searchTerm) {
        searchField << searchTerm
        searchField << Keys.ENTER
    }

}
