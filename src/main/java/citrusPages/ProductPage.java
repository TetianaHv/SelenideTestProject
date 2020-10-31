package citrusPages;

import citrusPages.fragments.HeaderFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import citrusPages.fragments.BasketFragment;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {
    BasketFragment basketFragment = new BasketFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    SelenideElement productPrice = $x("//div[@class='price']/span/span");
    SelenideElement buyButton = $x("//button[@class='btn orange full'][contains(text(), 'Купить')]");

    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductPage clickBuyButton() {
        buyButton.shouldBe(Condition.enabled).click();
        return this;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }
}
