package ui.citrus.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchFragment {
    SelenideElement searchInput = $("#search-input");
    SelenideElement searchButtonSubmit = $(".global-search__submit");

    public void searchProductByName(String productName) {
        if (!searchInput.getValue().equals("")) {
            searchInput.clear();
        }
        searchInput.val(productName);
//        searchInput.val(productName).pressEnter();
        searchButtonSubmit.click();
    }
}
