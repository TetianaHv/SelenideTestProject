package citrusPages.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderFragment {
    SelenideElement basketIcon = $x("//i[@class='icon-new-citrus-cart']");
    SelenideElement comparisonIcon = $x("//i[@class='icon-comparison2']");


    public void clickOnMainBasketIcon() {
        basketIcon.click();
    }

    public void clickOnMainComparisonIcon() {
        comparisonIcon.click();
    }
}
