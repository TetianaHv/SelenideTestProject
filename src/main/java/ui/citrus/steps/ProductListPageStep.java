package ui.citrus.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ui.citrus.pages.ProductListPage;

import static org.testng.Assert.assertTrue;


public class ProductListPageStep {
    ProductListPage productListPage = new ProductListPage();

    @Step("Click on product")
    public void clickOnProduct(String productName) {
        productListPage.waitForPageToLoad()
                .closePopup()
                .clickOnProductByName(productName);
    }

    @Step("Add product to basket")
    public String addProductToBasket(String productName) {
        productListPage.waitForPageToLoad()
                .closePopup();
        productListPage.addProductToBasket(productName);
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().closeBasketWidget();
        return productListPage.getProductPriceFromSearchResultByName(productName);
    }

    @Step("Remember product name")
    public String rememberProductName(int n) {
        return productListPage.getProductNameFromSearchResultByCount(n);
    }

    @Step("Remember product name")
    public String rememberProductName(int n, String menuName) {
        return productListPage.getProductNameByCount(n, menuName);
    }

    @Step("Add product to comparison")
    public String addProductToComparisonFromSearchResult(String productName) {
        productListPage.addProductFromSearchResultToComparison(productName)
                .waitForPageToLoad();
        return productListPage.getProductPriceFromSearchResultByName(productName);
    }

    @Step("Add product to comparison")
    public String addProductToComparison(String productName) {
        productListPage.addProductToComparison(productName)
                .waitForPageToLoad();
        return productListPage.getProductPriceByName(productName);
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName, String productPrice) {
        productListPage.getHeaderFragment().clickOnMainBasketIcon();
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
        productListPage.getBasketFragment().closeBasketWidget();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName1, String productPrice1, String productName2, String productPrice2, String totalPrice) {
        productListPage.getHeaderFragment().clickOnMainBasketIcon();
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName1));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(productName2));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(productPrice1));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(productPrice2));
        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalPrice));
        productListPage.getBasketFragment().closeBasketWidget();
    }

    @Step("Set price filter")
    public void setPriceFilter(String minFilterPrice, String maxFilterPrice) {
        productListPage.waitForPageToLoad()
                .getFilterFragment().setMinimumPriceFilter(minFilterPrice);
        Selenide.sleep(50);
        productListPage.waitForPageToLoad()
                .getFilterFragment().setMaximumPriceFilter(maxFilterPrice);
        productListPage.waitForPageToLoad();
    }


    @Step("Verify product names list content")
    public void verifyProductNamesByFilter(String menuName) {
        assertTrue(productListPage.isProductNameConsistsFilterName(menuName));
    }

    @Step("Verify product names list content")
    public void verifyProductNamesByFilter(String filterName1, String filterName2) {
        assertTrue(productListPage.isProductNameConsistsFilterName(filterName1, filterName2));
    }

    @Step("Verify product prices list content")
    public void verifyProductPricesByFilter(String minFilterPrice, String maxFilterPrice) {
        assertTrue(productListPage.isProductPriceInsideOfFilterRange(minFilterPrice, maxFilterPrice));
    }

    @Step("Set filter")
    public void setFilter(String block, String filter) {
        productListPage.waitForPageToLoad()
                .getFilterFragment().addFilterByName(block, filter);
        productListPage.waitForPageToLoad();
    }

    @Step("Verify product attributes content")
    public void verifyProductAttributesByFilter(String block, String filter) {
        assertTrue(productListPage.isProductHasBodyMaterialFilter(block, filter));
    }
}
