package citrus;

import citrusPages.HomePage;
import citrusPages.ProductListPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class FilterTest {
    HomePage homePage;
    ProductListPage productListPage;

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
        homePage = new HomePage();
        productListPage = new ProductListPage();

    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        refresh();
    }

    @Test
    public void priceFilterTest() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName1);
        productListPage.waitForPageToLoad()
                .getFilterFragment().setMinimumPriceFilter(minFilterPrice);
        productListPage.waitForPageToLoad()
                .getFilterFragment().setMaximumPriceFilter(maxFilterPrice);
        productListPage.waitForPageToLoad();

        assertTrue(productListPage.isProductNameConsistsFilterName(menuName1));
        assertTrue(productListPage.isProductPriceInsideOfFilterRange(minFilterPrice, maxFilterPrice));
    }

    @Test
    public void memorySizeFilterTest() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName2);
        productListPage.waitForPageToLoad()
                .getFilterFragment().addFilterByName(memoryFilterBlock, memorySizeFilter1);
        productListPage.waitForPageToLoad()
                .getFilterFragment().addFilterByName(memoryFilterBlock, memorySizeFilter2);
        productListPage.waitForPageToLoad();

        assertTrue(productListPage.isProductNameConsistsFilterName(menuName2));
        assertTrue(productListPage.isProductNameConsistsFilterName(memorySizeFilter1, memorySizeFilter2));
    }

    @Test
    public void bodyMaterialFilterTest() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName3);
        productListPage.waitForPageToLoad()
                .getFilterFragment().addFilterByName(bodyMaterialFilterBlock, bodyMaterial);
        productListPage.waitForPageToLoad()
                .closePopup();

        assertTrue(productListPage.isProductNameConsistsFilterName(menuName3));
        assertTrue(productListPage.isProductHasBodyMaterialFilter(bodyMaterialFilterBlock, bodyMaterial));
    }
}
