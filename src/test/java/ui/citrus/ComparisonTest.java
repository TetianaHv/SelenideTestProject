package ui.citrus;

import ui.citrus.pages.ComparisonPage;
import ui.citrus.pages.HomePage;
import ui.citrus.pages.ProductListPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class ComparisonTest {
    HomePage homePage;
    ProductListPage productListPage;
    ComparisonPage comparisonPage;

    String mainMenuName = "Ноутбуки, планшеты, МФУ";
    String menuName = "Acer";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        comparisonPage = new ComparisonPage();
    }

    @Test
    public void addTwoAndOneProductsToComparison() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName);
        productListPage.waitForPageToLoad()
                .closePopup();

        String productName1 = productListPage.getProductNameByCount(0, menuName);
        String productPrice1 = productListPage.getProductPriceByName(productName1);
        productListPage.addProductToComparison(productName1)
                .waitForPageToLoad();

        String productName2 = productListPage.getProductNameByCount(1, menuName);
        String productPrice2 = productListPage.getProductPriceByName(productName2);
        productListPage.addProductToComparison(productName2)
                .waitForPageToLoad();

        productListPage.getHeaderFragment().clickOnMainComparisonIcon();
        comparisonPage.waitForPageToLoad()
                .closePopup();

        comparisonPage.getComparisonItemsList().shouldHaveSize(2);
        comparisonPage.getComparisonItemsNameByCount(0).shouldHave(Condition.attribute("title", productName1));
        comparisonPage.getComparisonItemsNameByCount(2).shouldHave(Condition.attribute("title", productName2));
        comparisonPage.getComparisonItemsPriceByCount(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getComparisonItemsPriceByCount(2).shouldHave(Condition.text(productPrice2));

        comparisonPage.getProductSelectionFragment().clickAddNewProductButton();
        String productName3 = comparisonPage.getProductSelectionFragment().getProductNameByCount(0);
        String productPrice3 = comparisonPage.getProductSelectionFragment().getProductPriceByCount(0);
        comparisonPage.getProductSelectionFragment().clickNewProductCheckboxByCount(0)
                .clickAddButton();
        comparisonPage.waitForPageToLoad()
                .closePopup();

        Selenide.sleep(50);
        comparisonPage.getComparisonItemsList().shouldHaveSize(3);
        comparisonPage.getComparisonItemsNameByCount(0).shouldHave(Condition.attribute("title", productName1));
        comparisonPage.getComparisonItemsNameByCount(4).shouldHave(Condition.attribute("title", productName2));
        comparisonPage.getComparisonItemsNameByCount(2).shouldHave(Condition.attribute("title", productName3));
        comparisonPage.getComparisonItemsPriceByCount(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getComparisonItemsPriceByCount(4).shouldHave(Condition.text(productPrice2));
        comparisonPage.getComparisonItemsPriceByCount(2).shouldHave(Condition.text(productPrice3));
    }
}
