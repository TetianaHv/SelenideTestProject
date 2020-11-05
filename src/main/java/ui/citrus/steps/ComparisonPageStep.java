package ui.citrus.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ui.citrus.pages.ComparisonPage;
import ui.citrus.pages.ProductListPage;

import java.util.ArrayList;

public class ComparisonPageStep {
    ProductListPage productListPage = new ProductListPage();
    ComparisonPage comparisonPage = new ComparisonPage();

    @Step("Add product to basket")
    public void addProductToBasket(String productName) {
        productListPage.getHeaderFragment().clickOnMainComparisonIcon();
        comparisonPage.waitForPageToLoad()
                .closePopup();
        comparisonPage.addProductToBasket(productName);
        comparisonPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        comparisonPage.getBasketFragment().closeBasketWidget();
    }

    @Step("Add new product to comparison")
    public ArrayList<String> addNewProductToComparison(int i) {
        comparisonPage.getProductSelectionFragment().clickAddNewProductButton();
        ArrayList<String> list = new ArrayList<>();
        list.add(comparisonPage.getProductSelectionFragment().getProductNameByCount(i));
        list.add(comparisonPage.getProductSelectionFragment().getProductPriceByCount(i));
        comparisonPage.getProductSelectionFragment().clickNewProductCheckboxByCount(i)
                .clickAddButton();
        Selenide.sleep(50);
        return list;
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName1, String productPrice1, String productName2, String productPrice2) {
        productListPage.getHeaderFragment().clickOnMainComparisonIcon();
        comparisonPage.getComparisonItemsList().shouldHaveSize(2);
        comparisonPage.getComparisonItemsNameByCount(0).shouldHave(Condition.attribute("title", productName1));
        comparisonPage.getComparisonItemsNameByCount(2).shouldHave(Condition.attribute("title", productName2));
        comparisonPage.getComparisonItemsPriceByCount(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getComparisonItemsPriceByCount(2).shouldHave(Condition.text(productPrice2));
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName1, String productPrice1, String productName2, String productPrice2, String totalPrice) {
        comparisonPage.getHeaderFragment().clickOnMainBasketIcon();
        comparisonPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName1));
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(productName2));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(productPrice2));
        comparisonPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalPrice));
        comparisonPage.getBasketFragment().closeBasketWidget();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName1, String productPrice1, String productName2, String productPrice2, String productName3, String productPrice3) {
        comparisonPage.getComparisonItemsList().shouldHaveSize(3);
        comparisonPage.getComparisonItemsNameByCount(0).shouldHave(Condition.attribute("title", productName1));
        comparisonPage.getComparisonItemsNameByCount(4).shouldHave(Condition.attribute("title", productName2));
        comparisonPage.getComparisonItemsNameByCount(2).shouldHave(Condition.attribute("title", productName3));
        comparisonPage.getComparisonItemsPriceByCount(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getComparisonItemsPriceByCount(4).shouldHave(Condition.text(productPrice2));
        comparisonPage.getComparisonItemsPriceByCount(2).shouldHave(Condition.text(productPrice3));
    }
}
