package ui.citrus.pages;

import ui.citrus.fragments.SearchFragment;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {
    SearchFragment searchFragment = new SearchFragment();

    public HomePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomePage closePopup() {
        super.closePopup();
        return this;
    }

    public HomePage hoverMenuLine(String menuText) {
        $x("//a[@title='" + menuText + "']").scrollTo();
        $$x("//a[@title='" + menuText + "']").get(1).hover();
        return this;
    }

    public HomePage clickLinkInMenu(String linkText) {
        $x("//a[@title='" + linkText + "']/span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

}
