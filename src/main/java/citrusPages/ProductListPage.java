package citrusPages;

import citrusPages.fragments.BasketFragment;
import citrusPages.fragments.FilterFragment;
import citrusPages.fragments.HeaderFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductListPage extends BasePage {
    BasketFragment basketFragment = new BasketFragment();
    HeaderFragment headerFragment = new HeaderFragment();
    FilterFragment filterFragment = new FilterFragment();

    ElementsCollection productListFromSearchResult = $$x("//div[@class='short-itm-desc']");
    ElementsCollection productNamesList = $$x("//div[@class='product-card__name']/a");
    ElementsCollection productPriceList = $$x("//div[@class='prices__price']/span[@class='price']");
    ElementsCollection productCart = $$x("//div[@class='product-card__overview']");

    public ProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ProductListPage closePopup() {
        super.closePopup();
        return this;
    }

    public ProductListPage clickOnProductByName(String productName) {
        $x("//span[contains(text(),'" + productName + "')]").click();
        return this;
    }

    public String getProductNameByCount(int n, String name) {
        productNamesList.get(0).shouldHave(Condition.text(name));
        return productNamesList.get(n).getAttribute("title");
    }

    public String getProductPriceByName(String productName) {
        return $x("//a[contains(@title,'" + productName + "')]//..//..//div[@class='prices__price']/span").getText();
    }

    public String getProductPriceFromSearchResultByName(String productName) {
        return $x("//a[contains(@title,'" + productName + "')]/..//div[@class='base-price']/span").getText();
    }

    public String getProductNameFromSearchResultByCount(int n) {
        return productListFromSearchResult.get(n).$(By.tagName("h5")).getText();
    }

    public boolean isProductNameConsistsFilterName(String filterNameValue) {
        productNamesList.get(0).scrollTo();
        for (SelenideElement name : productNamesList) {
            String itemName = name.getAttribute("title");
            if (itemName.contains(filterNameValue)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isProductNameConsistsFilterName(String filterNameValue1, String filterNameValue2) {
        productNamesList.get(0).scrollTo();
        for (SelenideElement name : productNamesList) {
            String itemName = name.getAttribute("title");
            if (itemName.contains(filterNameValue1) || itemName.contains(filterNameValue2)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isProductPriceInsideOfFilterRange(String minFilterPrice, String maxFilterPrice) {
        productPriceList.get(0).scrollTo();
        for (SelenideElement price : productPriceList) {
            String str = price.getText().replaceAll(" ", "");
            if (Integer.parseInt(minFilterPrice) <= Integer.parseInt(str) && Integer.parseInt(maxFilterPrice) >= Integer.parseInt(str)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isProductHasBodyMaterialFilter(String block, String bodyMaterial) {
        for (int j = 0; j < productCart.size(); j++) {
            productCart.get(j).scrollTo();
            productNamesList.get(j).hover();
            String name = String.valueOf(productNamesList.get(j).getAttribute("title"));
            ElementsCollection propertyNames = $$x("//a[contains(@title, '" + name + "')]//..//..//..//..//span[@class='item__name']");
            ElementsCollection propertyValues = $$x("//a[contains(@title, '" + name + "')]//..//..//..//..//span[@class='item__value']");
            if (propertyNames.get(0).getText().contains(block) && propertyValues.get(0).getText().contains(bodyMaterial)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }
}
