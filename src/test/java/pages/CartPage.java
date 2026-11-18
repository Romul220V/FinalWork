package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage {
    private SelenideElement pageTitle = $("[data-test='title']");
    private SelenideElement itemName = $(".inventory_item_name");
    private SelenideElement checkoutButton = $("#checkout");

    @Step("Проверить, что в корзине находится товар: {expectedProductName}")
    public CartPage checkProductInCart(String expectedProductName) {
        checkHeaderLogo();
        pageTitle.shouldBe(visible).shouldHave(text("Your Cart"));
        itemName.shouldBe(visible).shouldHave(text(expectedProductName));
        return this;
    }

    @Step("Нажать кнопку Checkout для оформления заказа")
    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
