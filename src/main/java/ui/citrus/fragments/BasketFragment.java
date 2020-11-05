package ui.citrus.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasketFragment {
    SelenideElement basketWidget = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection productNames1 = $$x("//a[@class='ex-active active ctrs-basket-product__name']");
    ElementsCollection productNames = $$x("//a[@class='ctrs-basket-product__name']");
    SelenideElement basketTotalPrice = $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']");
    SelenideElement closeBasketWidgetButton = $x("//div[@class='el-dialog el-dialog--medium']//button[@class='el-dialog__headerbtn']");
    ElementsCollection productPrice = $$x("//div[@class='ctrs-basket-item__total-price']/span");

    public SelenideElement getBasket() {
        return basketWidget;
    }

    public ElementsCollection getProductNamesFromBasket1() {
        return productNames1;
    }

    public ElementsCollection getProductNamesFromBasket() {
        return productNames;
    }

    public SelenideElement getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public void closeBasketWidget() {
        closeBasketWidgetButton.click();
    }

    public ElementsCollection getProductPricesFromBasket() {
        return productPrice;
    }
}
