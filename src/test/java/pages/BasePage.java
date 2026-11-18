package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    private SelenideElement appLogo = $(".app_logo");
    private SelenideElement cartLink = $(".shopping_cart_link");
    private SelenideElement cartBadge = $(".shopping_cart_badge");

    @Step("Проверить, что логотип в шапке отображается")
    public void checkHeaderLogo() {
        appLogo.shouldBe(visible).shouldHave(text("Swag Labs"));
    }

    @Step("Перейти в корзину")
    public void openCart() {
        cartLink.click();
    }

    @Step("Проверить количество товаров на значке корзины: {expectedCount}")
    public void checkCartBadgeCount(int expectedCount) {
        cartBadge.shouldBe(visible).shouldHave(text(String.valueOf(expectedCount)));
    }
}