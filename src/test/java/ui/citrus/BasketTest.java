package ui.citrus;

import ui.citrus.pages.ComparisonPage;
import ui.citrus.pages.HomePage;
import ui.citrus.pages.ProductListPage;
import ui.citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;

public class BasketTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparisonPage comparisonPage;

    String mainMenuName = "Смартфоны";
    String menuName = "Apple";
    String productName = "Apple iPhone 11 128Gb Black";
    String productTestName = "Apple iPhone";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
        comparisonPage = new ComparisonPage();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        refresh();
    }

    @Test
    public void addProductToBasketViaMenu() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine(mainMenuName)
                .clickLinkInMenu(menuName);
        productListPage.waitForPageToLoad()
                .closePopup()
                .clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickBuyButton()
                .getBasketFragment().closeBasketWidget();

        productPage.getHeaderFragment().clickOnMainBasketIcon();
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket1().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket1().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
        productPage.getBasketFragment().closeBasketWidget();
    }

    @Test
    public void addProductToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .closePopup()
                .getSearchFragment().searchProductByName(productName);
        String productPrice = productListPage.waitForPageToLoad()
                .closePopup()
                .getProductPriceFromSearchResultByName(productName);
        productListPage.addProductToBasket(productName);
        productListPage.getBasketFragment().closeBasketWidget();

        productListPage.getHeaderFragment().clickOnMainBasketIcon();
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
        productListPage.getBasketFragment().closeBasketWidget();
    }

    @Test
    public void addTwoProductsToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .closePopup()
                .getSearchFragment().searchProductByName(productTestName);
        productListPage.waitForPageToLoad()
                .closePopup();
        String productName1 = productListPage.getProductNameFromSearchResultByCount(0);
        String productPrice1 = productListPage.getProductPriceFromSearchResultByName(productName1);
        productListPage.addProductToBasket(productName1);
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().closeBasketWidget();

        String productName2 = productListPage.getProductNameFromSearchResultByCount(1);
        String productPrice2 = productListPage.getProductPriceFromSearchResultByName(productName2);
        productListPage.addProductToBasket(productName2);
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().closeBasketWidget();

        String totalPrice = String.valueOf((Integer.parseInt(productPrice1.replaceAll(" ", ""))
                + Integer.parseInt(productPrice2.replaceAll(" ", ""))));
        totalPrice = totalPrice.substring(0, 2) + " " + totalPrice.substring(2);

        productListPage.getHeaderFragment().clickOnMainBasketIcon();
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName1));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(productName2));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(productPrice1));
        productListPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(productPrice2));
        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalPrice));
        productListPage.getBasketFragment().closeBasketWidget();
    }

    @Test
    public void addTwoProductsToBasketFromComparison() {
        homePage.waitForPageToLoad()
                .closePopup()
                .getSearchFragment().searchProductByName(productTestName);
        productListPage.waitForPageToLoad()
                .closePopup();
        String productName1 = productListPage.getProductNameFromSearchResultByCount(0);
        String productPrice1 = productListPage.getProductPriceFromSearchResultByName(productName1);
        productListPage.addProductFromSearchResultToComparison(productName1);

        String productName2 = productListPage.getProductNameFromSearchResultByCount(1);
        String productPrice2 = productListPage.getProductPriceFromSearchResultByName(productName2);
        productListPage.addProductFromSearchResultToComparison(productName2);

        String totalPrice = String.valueOf((Integer.parseInt(productPrice1.replaceAll(" ", ""))
                + Integer.parseInt(productPrice2.replaceAll(" ", ""))));
        totalPrice = totalPrice.substring(0, 2) + " " + totalPrice.substring(2);

        productListPage.getHeaderFragment().clickOnMainComparisonIcon();

        comparisonPage.waitForPageToLoad()
                .closePopup();
        comparisonPage.addProductToBasket(productName1);
        comparisonPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        comparisonPage.getBasketFragment().closeBasketWidget();

        comparisonPage.addProductToBasket(productName2);
        comparisonPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        comparisonPage.getBasketFragment().closeBasketWidget();

        comparisonPage.getHeaderFragment().clickOnMainBasketIcon();
        comparisonPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName1));
        comparisonPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(productName2));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(0).shouldHave(Condition.text(productPrice1));
        comparisonPage.getBasketFragment().getProductPricesFromBasket().get(1).shouldHave(Condition.text(productPrice2));
        comparisonPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalPrice));
        comparisonPage.getBasketFragment().closeBasketWidget();
    }
}
