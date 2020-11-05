package ui.citrusWithAllureSteps;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.citrus.steps.ComparisonPageStep;
import ui.citrus.steps.HomePageStep;
import ui.citrus.steps.ProductListPageStep;
import ui.citrus.steps.ProductPageStep;

import static com.codeborne.selenide.Selenide.*;
//mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Test.xml
//mvn allure:report

public class BasketTest {
    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;
    ProductPageStep productPageStep;
    ComparisonPageStep comparisonPageStep;

    String mainMenuName = "Смартфоны";
    String menuName = "Apple";
    String productName = "Apple iPhone 11 128Gb Black";
    String productTestName = "Apple iPhone";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePageStep = new HomePageStep();
        productListPageStep = new ProductListPageStep();
        productPageStep = new ProductPageStep();
        comparisonPageStep = new ComparisonPageStep();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        refresh();
    }

    @Test
    public void addProductToBasketViaSearchResults() {
        homePageStep.searchProduct(productName);
        String productPrice = productListPageStep.addProductToBasket(productName);
        productListPageStep.verifyBasketContent(productName, productPrice);
    }

    @Test
    public void addProductToBasketViaMenu() {
        homePageStep.clickOnLinkInMenu(mainMenuName, menuName);
        productListPageStep.clickOnProduct(productName);

        String productPrice = productPageStep.addProductToBasket();
        productPageStep.verifyBasketContent(productName, productPrice);
    }

    @Test
    public void addTwoProductsToBasketViaSearchResults() {
        homePageStep.searchProduct(productTestName);

        String productName1 = productListPageStep.rememberProductName(0);
        String productPrice1 = productListPageStep.addProductToBasket(productName1);
        String productName2 = productListPageStep.rememberProductName(1);
        String productPrice2 = productListPageStep.addProductToBasket(productName2);

        String totalPrice = String.valueOf((Integer.parseInt(productPrice1.replaceAll(" ", ""))
                + Integer.parseInt(productPrice2.replaceAll(" ", ""))));
        totalPrice = totalPrice.substring(0, 2) + " " + totalPrice.substring(2);

        productListPageStep.verifyBasketContent(productName1, productPrice1, productName2, productPrice2, totalPrice);
    }

    @Test
    public void addTwoProductsToBasketFromComparison() {
        homePageStep.searchProduct(productTestName);

        String productName1 = productListPageStep.rememberProductName(0);
        String productPrice1 = productListPageStep.addProductToComparisonFromSearchResult(productName1);
        String productName2 = productListPageStep.rememberProductName(1);
        String productPrice2 = productListPageStep.addProductToComparisonFromSearchResult(productName2);

        String totalPrice = String.valueOf((Integer.parseInt(productPrice1.replaceAll(" ", ""))
                + Integer.parseInt(productPrice2.replaceAll(" ", ""))));
        totalPrice = totalPrice.substring(0, 2) + " " + totalPrice.substring(2);

        comparisonPageStep.addProductToBasket(productName1);
        comparisonPageStep.addProductToBasket(productName2);
        comparisonPageStep.verifyBasketContent(productName1, productPrice1, productName2, productPrice2, totalPrice);
    }
}
