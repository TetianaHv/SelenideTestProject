package citrus;

import citrusPages.HomePage;
import citrusPages.ProductListPage;
import citrusPages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class BasketTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;

    String productName = "Apple iPhone 11 128Gb Black";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
    }

    @BeforeMethod
    public void cleanBasket(){
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    public void addProductToBasketViaMenu() {
        homePage.waitForPageToLoad()
                .closePopup()
                .hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Apple");
        productListPage.waitForPageToLoad()
                .closePopup()
                .clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickBuyButton();

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket1().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket1().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addProductToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .closePopup()
                .getSearchFragment().searchProduct(productName);
        String productPrice = productListPage.waitForPageToLoad()
                .closePopup()
                .getProductPriceByName(productName);
        productListPage.addProductToBasket(productName);

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket2().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket2().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }
}
