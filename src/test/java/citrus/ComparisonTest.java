package citrus;

import citrusPages.ComparisonPage;
import citrusPages.HomePage;
import citrusPages.ProductListPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class ComparisonTest {
    HomePage homePage;
    ProductListPage productListPage;
    ComparisonPage comparisonPage;

    String mainMenuName = "Ноутбуки, планшеты, МФУ";
    String menuName = "Acer";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        comparisonPage = new ComparisonPage();
    }

    @Test
    public void addTwoAndOneProductsToComparison() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName);
        productListPage.waitForPageToLoad()
                .closePopup();

        String productName1 = productListPage.getProductNameByCount(0, menuName);
        String productPrice1 = productListPage.getProductPriceByName(productName1);
        productListPage.addProductToComparison(productName1)
                .waitForPageToLoad();

        String productName2 = productListPage.getProductNameByCount(1, menuName);
        String productPrice2 = productListPage.getProductPriceByName(productName2);
        productListPage.addProductToComparison(productName2)
                .waitForPageToLoad();

        productListPage.getHeaderFragment().clickOnMainComparisonIcon();
        comparisonPage.waitForPageToLoad()
                .closePopup();

        assertEquals(comparisonPage.getComparisonItemsListSize(), 2);
        assertEquals(comparisonPage.getComparisonItemsNameByCount(0), productName1);
        assertEquals(comparisonPage.getComparisonItemsNameByCount(2), productName2);
        assertEquals(comparisonPage.getComparisonItemsPriceByCount(0), productPrice1);
        assertEquals(comparisonPage.getComparisonItemsPriceByCount(2), productPrice2);

        comparisonPage.getProductSelectionFragment().clickAddNewProductButton();
        String productName3 = comparisonPage.getProductSelectionFragment().getProductNameByCount(0);
        String productPrice3 = comparisonPage.getProductSelectionFragment().getProductPriceByCount(0);
        comparisonPage.getProductSelectionFragment().clickNewProductCheckboxByCount(0)
                .clickAddButton();
        comparisonPage.waitForPageToLoad()
                .closePopup();

        Selenide.sleep(50);
        assertEquals(comparisonPage.getComparisonItemsListSize(), 3);
        assertEquals(comparisonPage.getComparisonItemsNameByCount(0), productName1);
        assertEquals(comparisonPage.getComparisonItemsNameByCount(4), productName2);
        assertEquals(comparisonPage.getComparisonItemsNameByCount(2), productName3);
        assertEquals(comparisonPage.getComparisonItemsPriceByCount(0), productPrice1);
        assertEquals(comparisonPage.getComparisonItemsPriceByCount(4), productPrice2);
        assertEquals(comparisonPage.getComparisonItemsPriceByCount(2), productPrice3);
    }
}
