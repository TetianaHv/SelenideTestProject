package citrusPages;

import citrusPages.fragments.BasketFragment;
import citrusPages.fragments.HeaderFragment;
import citrusPages.fragments.ProductSelectionFragment;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage extends BasePage {
    BasketFragment basketFragment = new BasketFragment();
    HeaderFragment headerFragment = new HeaderFragment();
    ProductSelectionFragment productSelectionFragment = new ProductSelectionFragment();

    ElementsCollection comparisonItemsBlocks = $$x("//div[@class='relative']");

    public ComparisonPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ComparisonPage closePopup() {
        super.closePopup();
        return this;
    }

    public int getComparisonItemsListSize() {
        comparisonItemsBlocks.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return comparisonItemsBlocks.size();
    }

    public String getComparisonItemsNameByCount(int n) {
        return $$x("//div[@class='short-itm-desc']/a").get(n).getAttribute("title");
    }

    public String getComparisonItemsPriceByCount(int n) {
        return $$x("//div[@class='short-itm-desc']//span[@class='price-number']").get(n).getText();
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
