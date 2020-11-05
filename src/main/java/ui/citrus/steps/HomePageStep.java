package ui.citrus.steps;

import io.qameta.allure.Step;
import ui.citrus.pages.HomePage;


public class HomePageStep {
    HomePage homePage = new HomePage();

    @Step("Click on Link in menu")
    public void clickOnLinkInMenu(String maneMenuName, String menuName) {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(maneMenuName)
                .clickLinkInMenu(menuName);
    }

    @Step("Search product")
    public void searchProduct(String productName) {
        homePage.waitForPageToLoad()
                .closePopup()
                .getSearchFragment().searchProductByName(productName);
    }
}
