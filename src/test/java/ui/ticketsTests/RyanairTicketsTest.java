package ui.ticketsTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class RyanairTicketsTest {
    @Test
    public void searchTicketsWizzair() {
        Configuration.baseUrl = "https://www.ryanair.com/us/en";
        Configuration.timeout = 10000;

        open("/");
        $(By.id("input-button__departure")).clear();
        $(By.id("input-button__departure")).val("Vienna");
        $x("//span[@data-ref='airport-item__name' and contains(text(), 'Vienna')]").click();
        $(By.id("input-button__destination")).val("Kyiv");
        $x("//span[@data-ref='airport-item__name' and contains(text(), 'Kyiv')]").click();

        $$("div.input-button__input.ng-star-inserted").first().click();
        $x("//div[@data-id='2020-11-19']").click();
        $x("//div[@data-id='2020-11-21']").click();
        $$x("//div[@class='counter__button-wrapper--enabled']").first().click();
        $x("//ry-spinner[contains(text(), ' Search ')]").click();

        $$(By.tagName("journey-container")).shouldHaveSize(2);
        $$(By.tagName("journey-container")).get(0).$(By.tagName("h3")).shouldHave(Condition.text(" Vienna to Kyiv  "));
        $$("span.date-item__day-of-month.h4").get(0).shouldHave(Condition.text("19"));
        $$("span.date-item__month.h4").get(0).shouldHave(Condition.text("Nov"));
        $$(By.tagName("journey-container")).get(1).$(By.tagName("h3")).shouldHave(Condition.text(" Kyiv to Vienna  "));
        $$("span.date-item__day-of-month.h4").get(1).shouldHave(Condition.text("21"));
        $$("span.date-item__month.h4").get(1).shouldHave(Condition.text("Nov"));
    }
}
