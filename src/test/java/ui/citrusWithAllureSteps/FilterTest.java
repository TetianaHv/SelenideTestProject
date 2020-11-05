package ui.citrusWithAllureSteps;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.citrus.steps.HomePageStep;
import ui.citrus.steps.ProductListPageStep;

import static com.codeborne.selenide.Selenide.*;

public class FilterTest {
    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;

    String mainMenuName = "Смартфоны";
    String menuName1 = "Samsung";
    String minFilterPrice = "6000";
    String maxFilterPrice = "15000";
    String menuName2 = "Xiaomi";
    String memoryFilterBlock = "Объем встроенной памяти";
    String memorySizeFilter1 = "32";
    String memorySizeFilter2 = "64";
    String menuName3 = "Huawei";
    String bodyMaterialFilterBlock = "Материалы корпуса";
    String bodyMaterial = "Металл";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePageStep = new HomePageStep();
        productListPageStep = new ProductListPageStep();

    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        refresh();
    }

    @Test
    public void priceFilterTest() {
        homePageStep.clickOnLinkInMenu(mainMenuName, menuName1);
        productListPageStep.setPriceFilter(minFilterPrice, maxFilterPrice);
        productListPageStep.verifyProductNamesByFilter(menuName1);
        productListPageStep.verifyProductPricesByFilter(minFilterPrice, maxFilterPrice);
    }

    @Test
    public void memorySizeFilterTest() {
        homePageStep.clickOnLinkInMenu(mainMenuName, menuName2);
        productListPageStep.setFilter(memoryFilterBlock, memorySizeFilter1);
        productListPageStep.setFilter(memoryFilterBlock, memorySizeFilter2);
        productListPageStep.verifyProductNamesByFilter(menuName2);
        productListPageStep.verifyProductNamesByFilter(memorySizeFilter1, memorySizeFilter2);
    }

    @Test
    public void bodyMaterialFilterTest() {
        homePageStep.clickOnLinkInMenu(mainMenuName, menuName3);
        productListPageStep.setFilter(bodyMaterialFilterBlock, bodyMaterial);
        productListPageStep.verifyProductNamesByFilter(menuName3);
        productListPageStep.verifyProductAttributesByFilter(bodyMaterialFilterBlock, bodyMaterial);
    }
}
