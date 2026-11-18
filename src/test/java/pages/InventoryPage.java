package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InventoryPage extends BasePage {

    private SelenideElement sectionTitle = $(".title");
    private SelenideElement addBackpackButton = $("#add-to-cart-sauce-labs-backpack");
    private SelenideElement removeBackpackButton = $("#remove-sauce-labs-backpack");
    private SelenideElement sortDropdown = $("[data-test='product-sort-container']");


    @Step("Проверить, что открыта страница каталога товаров")
    public InventoryPage checkPageOpened() {
        checkHeaderLogo();
        sectionTitle.shouldBe(visible).shouldHave(text("Products"));
        return this;
    }

    @Step("Добавить рюкзак Sauce Labs Backpack в корзину")
    public InventoryPage addBackpackToCart() {
        addBackpackButton.click();
        removeBackpackButton.shouldBe(visible);
        return this;
    }

    @Step("Отсортировать товары по значению: {optionText}")
    public InventoryPage sortProductsBy(String optionText) {
        sortDropdown.selectOption(optionText);
        return this;
    }
}
