package ui.citrus.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    SelenideElement popupCloseButton = $(".el-dialog__close");

    public BasePage waitForPageToLoad() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public BasePage closePopup() {
        if (popupCloseButton.isDisplayed()) {
            popupCloseButton.click();
        }
        return this;
    }

    public BasePage addProductToBasket(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public BasePage addProductToComparison(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//..//..//span[contains(@class,'icon icon-comparison2')]").hover().click();
        return this;
    }

    public BasePage addProductFromSearchResultToComparison(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//i[@class='icon-comparison2 el-tooltip item']").click();
        return this;
    }
}
