package ui.citrus.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.citrus.pages.ProductPage;


public class ProductPageStep {
    ProductPage productPage = new ProductPage();

    @Step("Add product to basket")
    public String addProductToBasket() {
        productPage.clickBuyButton();
        return productPage.getProductPrice();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName, String productPrice) {
        productPage.getHeaderFragment().clickOnMainBasketIcon();
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket1().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket1().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }
}
