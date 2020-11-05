package ui.citrusWithAllureSteps;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.citrus.steps.ComparisonPageStep;
import ui.citrus.steps.HomePageStep;
import ui.citrus.steps.ProductListPageStep;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class ComparisonTest {
    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;
    ComparisonPageStep comparisonPageStep;

    String mainMenuName = "Ноутбуки, планшеты, МФУ";
    String menuName = "Acer";

    @BeforeClass
    public void start() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 10000;
        open("/");
        homePageStep = new HomePageStep();
        productListPageStep = new ProductListPageStep();
        comparisonPageStep = new ComparisonPageStep();
    }

    @Test
    public void addTwoAndOneProductsToComparison() {
        homePageStep.clickOnLinkInMenu(mainMenuName, menuName);

        String productName1 = productListPageStep.rememberProductName(0, menuName);
        String productPrice1 = productListPageStep.addProductToComparison(productName1);
        String productName2 = productListPageStep.rememberProductName(1, menuName);
        String productPrice2 = productListPageStep.addProductToComparison(productName2);

        comparisonPageStep.verifyBasketContent(productName1, productPrice1, productName2, productPrice2);
        List newProductAttributes = comparisonPageStep.addNewProductToComparison(0);

        comparisonPageStep.verifyBasketContent(productName1, productPrice1, productName2, productPrice2, (String) newProductAttributes.get(0), (String) newProductAttributes.get(1));
    }
}
