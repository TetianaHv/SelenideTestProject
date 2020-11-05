package ui.citrus.pages;

import ui.citrus.fragments.BasketFragment;
import ui.citrus.fragments.HeaderFragment;
import ui.citrus.fragments.ProductSelectionFragment;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class ComparisonPage extends BasePage {
    BasketFragment basketFragment = new BasketFragment();
    HeaderFragment headerFragment = new HeaderFragment();
    ProductSelectionFragment productSelectionFragment = new ProductSelectionFragment();

    ElementsCollection comparisonItemsBlocks = $$x("//div[@class='relative']");
    ElementsCollection itemNamesList = $$x("//div[@class='short-itm-desc']/a");
    ElementsCollection itemPricesList = $$x("//div[@class='short-itm-desc']//span[@class='price-number']");

    public ComparisonPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ComparisonPage closePopup() {
        super.closePopup();
        return this;
    }

    public ElementsCollection getComparisonItemsList() {
        comparisonItemsBlocks.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return comparisonItemsBlocks;
    }

    public SelenideElement getComparisonItemsNameByCount(int n) {
        return itemNamesList.get(n);
    }

    public SelenideElement getComparisonItemsPriceByCount(int n) {
        return itemPricesList.get(n);
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }

    public ProductSelectionFragment getProductSelectionFragment() {
        return productSelectionFragment;
    }
}
