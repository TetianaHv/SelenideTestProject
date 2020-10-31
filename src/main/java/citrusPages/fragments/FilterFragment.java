package citrusPages.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class FilterFragment {
    SelenideElement minimumPriceFilterTextBox = $x("//div[@class='half' and span[contains(text(), 'от')]]//input");
    SelenideElement maximumPriceFilterTextBox = $x("//div[@class='half' and span[contains(text(), 'до')]]//input");

    public FilterFragment setMinimumPriceFilter(String price) {
        minimumPriceFilterTextBox.shouldBe(Condition.visible).clear();
        minimumPriceFilterTextBox.shouldHave(Condition.value("0")).sendKeys(price);
        minimumPriceFilterTextBox.shouldHave(Condition.value(price));
        return this;
    }

    public FilterFragment setMaximumPriceFilter(String price) {
        maximumPriceFilterTextBox.shouldBe(Condition.visible).clear();
        maximumPriceFilterTextBox.sendKeys(price);
        return this;
    }

    public FilterFragment addFilterByName(String blockName, String filter) {
        $x("//div[contains(text(), '" + blockName + "')]").scrollTo();
        $x("//div[contains(text(), '" + blockName + "')]//..//a[contains(text(), '" + filter + "')]/../..//span[@class='el-checkbox__inner']").click();
        return this;
    }
}
