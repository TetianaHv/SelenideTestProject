package TicketsTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class FlyuiaTicketsTest {
    @Test
    public void searchTicketsWizzair() {
        Configuration.baseUrl = "https://www.flyuia.com/ua/en/home";
        Configuration.timeout = 1000000; //more time for verifying

        open("/");
        $x("//input[@placeholder='Departure']").click();
        $x("//input[@placeholder='Departure']").val("Vienna");
        $("div.list-item").click();

        $x("//input[@placeholder='Arrival']").click();
        $x("//input[@placeholder='Arrival']").val("Kyiv");
        $x("//span[@class='highlight']").shouldHave(Condition.text("Kyiv"));
        $("div.list-item").click();

        $$("div.value-as-text-container").get(2).click();
        $x("//span[@class='fx-flex-40']").shouldHave(Condition.text(" October  2020 "));
        $$("span.prev-next-btn").get(1).click();
        $x("//span[@class='fx-flex-40']").shouldHave(Condition.text(" November  2020 "));
        $x("//button[contains(text(), ' 19 ')]").click();

        $$("div.input-wrapper").get(3).click();
        $x("//span[@class='fx-flex-40']").shouldHave(Condition.text(" November  2020 "));
        $x("//button[contains(text(), ' 22 ')]").click();

        $$("div.input-wrapper").get(4).click();
        $$x("//button[contains(text(), '+ ')] ").first().click();
        $(By.id("SEARCH_WIDGET_FORM_BUTTONS_SEARCH_FLIGHTS")).click();

        switchTo().window(1);

        $("i.icon-close").click();

        $(By.className("outbound-section")).shouldBe(Condition.visible);
        $$(By.className("product__title")).first().shouldHave(Condition.text("Vienna - Kyiv"));
        $$x("//button[@class='date-container nonclickable ng-star-inserted']")
                .first().$(By.className("date-title")).shouldHave(Condition.text("Thu, 19 Nov"));
        $(By.className("inbound-section")).shouldBe(Condition.visible);
        $$(By.className("product__title")).get(1).shouldHave(Condition.text("Kyiv - Vienna"));
        $$x("//button[@class='date-container nonclickable ng-star-inserted']")
                .get(1).$(By.className("date-title")).shouldHave(Condition.text("Sun, 22 Nov"));
    }
}
