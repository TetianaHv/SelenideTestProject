package citrusPages.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductSelectionFragment {
    SelenideElement addNewProductToComparisonButton = $x("//span[@class='flex-column icon']");
    ElementsCollection addNewProductCheckboxesList = $$x("//span[@class='el-checkbox__input']");
    ElementsCollection productPricesList = $$x("//span[@class='price-new']//span[@class='price-number']");
    ElementsCollection productNamesList = $$x("//p[@class='product-name']");
    SelenideElement addButton = $x("//button[@class='el-button el-button--primary']");

    public ProductSelectionFragment clickAddNewProductButton() {
        addNewProductToComparisonButton.hover().click();
        return this;
    }

    public ProductSelectionFragment clickNewProductCheckboxByCount(int n) {
        addNewProductCheckboxesList.get(n).click();
        return this;
    }

    public String getProductNameByCount(int n) {
        return productNamesList.get(n).getText();
    }

    public String getProductPriceByCount(int n) {
        return productPricesList.get(n).getText();
    }

    public void clickAddButton() {
        addButton.click();
    }
}
